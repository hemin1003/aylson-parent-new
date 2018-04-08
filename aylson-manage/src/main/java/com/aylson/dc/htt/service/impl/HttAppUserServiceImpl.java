package com.aylson.dc.htt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.domain.Result;
import com.aylson.core.frame.domain.ResultCode;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttAppUserDao;
import com.aylson.dc.htt.dao.HttBlacklistHisDao;
import com.aylson.dc.htt.po.HttAppUser;
import com.aylson.dc.htt.search.HttAppUserSearch;
import com.aylson.dc.htt.search.HttWithdrawHisSearch;
import com.aylson.dc.htt.service.HttAppUserService;
import com.aylson.dc.htt.service.HttWithdrawHisService;
import com.aylson.dc.htt.vo.HttAppUserVo;
import com.aylson.dc.htt.vo.HttBlacklistHisVo;
import com.aylson.dc.htt.vo.HttWithdrawHisVo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;
import com.aylson.utils.UUIDUtils;

@Service
public class HttAppUserServiceImpl extends BaseServiceImpl<HttAppUser, HttAppUserSearch> implements HttAppUserService {

	protected static final Logger logger = Logger.getLogger(HttAppUserServiceImpl.class);

	@Autowired
	private HttAppUserDao appUserDao;

	@Autowired
	private HttWithdrawHisService httWithdrawHisService;

	@Autowired
	private HttBlacklistHisDao httBlacklistHisDao;

	@Override
	protected BaseDao<HttAppUser, HttAppUserSearch> getBaseDao() {
		return appUserDao;
	}

	@Override
	public Result importOfProcessBlackList(List<List<Object>> listob) {
		String cTime = DateUtil2.getCurrentLongDateTime();
		Result result = new Result();

		List<String> idList = new ArrayList<String>();
		for (List<Object> list : listob) {
			idList.add(String.valueOf(list.get(0)));
		}

		List<HttAppUserVo> appUserVoList = this.selectByIdList(idList);
		List<String> existPhoneNumList = new ArrayList<>();
		List<HttAppUserVo> existAppUserVoList = new ArrayList<>();
		List<HttBlacklistHisVo> existHttBlacklistHis = new ArrayList<>();

		for (List<Object> list : listob) {
			String phoneNum = String.valueOf(list.get(0));

			for (HttAppUserVo appUserVo : appUserVoList) {
				if (appUserVo != null) {
					if (appUserVo.getPhoneNum().equals(phoneNum)) {
						if (appUserVo.getBlackList() == 0) {
							// 设为黑名单用户
							appUserVo.setBlackList(1);
							appUserVo.setUpdateDate(cTime);
							appUserVo.setAccountStatus(2); // 0=正常; 1=疑似；2=封禁
							existPhoneNumList.add(phoneNum);
							existAppUserVoList.add(appUserVo);
							existHttBlacklistHis.add(this.addBlacklistHis(appUserVo.getPhoneNum(), cTime,
									StrUtil.null2Str(list.get(1))));
						}
						break; // 一旦遇到匹配id，则停止当前循环，进入下一个
					}
				} else {
					logger.info("用户ID的phoneNum=" + phoneNum + ", 无此用户记录，跳过处理。");
				}
			}
		}

		List<HttWithdrawHisVo> withdrawHisList = httWithdrawHisService.selectByPhoneNumList(existPhoneNumList);

		for (HttWithdrawHisVo httWithdrawHisVo : withdrawHisList) {
			// 只处理“提现状态是处理中”的提现记录
			if (httWithdrawHisVo.getStatusType() == 1) {
				httWithdrawHisVo.setStatusType(7);
				httWithdrawHisVo.setStatus("审核失败");
				httWithdrawHisVo.setUpdateDate(cTime);
			}
		}
		if (existHttBlacklistHis.size() > 0) {
			boolean flag1 = this.httBlacklistHisDao.batchInsert(existHttBlacklistHis);
			if (!flag1) {
				logger.error("记录黑名单导入的历史查询数据失败", new RuntimeException("flag1=" + flag1));
			}
		}
		if (existAppUserVoList.size() > 0) {
			boolean flag = this.batchUpdate(existAppUserVoList);
			if (flag) {
				flag = httWithdrawHisService.batchUpdateByPhoneNum(withdrawHisList);
				if (!flag) {
					logger.error("记录黑名单导入的提现处理数据更新失败", new RuntimeException("flag=" + flag));
				}
			}
		}
		logger.info("操作成功");
		result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		return result;
	}

	public HttBlacklistHisVo addBlacklistHis(String phoneNum, String cTime, String strategy) {
		HttBlacklistHisVo blacklistHisVo = new HttBlacklistHisVo();
		blacklistHisVo.setId(UUIDUtils.create());
		blacklistHisVo.setPhoneNum(phoneNum);
		blacklistHisVo.setSource(3);
		blacklistHisVo.setStrategy(strategy);
		blacklistHisVo.setAccountStatus(2);
		blacklistHisVo.setCreateDate(cTime);
		blacklistHisVo.setUpdateDate(cTime);
		return blacklistHisVo;
	}

	@Override
	public Result sendSms(List<String> phoneNumList) {
		Result result = new Result();
		//短信发送代码
		result.setOK(ResultCode.CODE_STATE_200, "发送完成");
		return result;
	}

	@Override
	public List<HttAppUserVo> selectByIdList(List<String> idList) {
		return this.appUserDao.selectByIdList(idList);
	}

	@Transactional
	@Override
	public boolean isBlackList(HttAppUserVo appUserVo) {
		boolean flag = this.edit(appUserVo);
		if(flag && appUserVo.getBlackList() == 0) {
			//拉取用户提现记录为“审核失败”的，回退给用户
			HttWithdrawHisSearch search = new HttWithdrawHisSearch();
			search.setPhoneNum(appUserVo.getPhoneNum());
			search.setWithdrawTimeBeyond("2018-01-15");	//固定值
			search.setStatusType(7);
			List<HttWithdrawHisVo> list = this.httWithdrawHisService.getList(search);
			for (HttWithdrawHisVo httWithdrawHisVo : list) {
				if(httWithdrawHisVo.getStatusType() == 7) {
					httWithdrawHisVo.setStatus("提现失败");
					httWithdrawHisVo.setStatusType(4);
					this.httWithdrawHisService.updateWithdrawHisInfo(httWithdrawHisVo);
				}
			}
		}
		return flag;
	}

	@Override
	public List<String> selectAllRealPhoneNum() {
		// TODO Auto-generated method stub
		return null;
	}

}