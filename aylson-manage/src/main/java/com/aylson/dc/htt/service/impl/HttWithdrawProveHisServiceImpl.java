package com.aylson.dc.htt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttWithdrawProveHisDao;
import com.aylson.dc.htt.po.HttAppUser;
import com.aylson.dc.htt.po.HttWithdrawProveHis;
import com.aylson.dc.htt.search.HttWithdrawProveHisSearch;
import com.aylson.dc.htt.service.HttAppUserService;
import com.aylson.dc.htt.service.HttWithdrawHisService;
import com.aylson.dc.htt.service.HttWithdrawProveHisService;
import com.aylson.dc.htt.vo.HttWithdrawHisVo;
import com.aylson.dc.sys.common.SessionInfo;
import com.aylson.utils.DateUtil2;

import net.sf.json.JSONObject;

@Service
public class HttWithdrawProveHisServiceImpl extends BaseServiceImpl<HttWithdrawProveHis, HttWithdrawProveHisSearch>
		implements HttWithdrawProveHisService {
	
	protected static final Logger logger = Logger.getLogger(HttWithdrawProveHisServiceImpl.class);

	@Autowired
	private HttWithdrawProveHisDao httWithdrawProveHisDao;

	@Autowired
	private HttAppUserService httAppUserService;
	
	@Autowired
	private HttWithdrawHisService httWithdrawHisService;

	@Override
	protected BaseDao<HttWithdrawProveHis, HttWithdrawProveHisSearch> getBaseDao() {
		return httWithdrawProveHisDao;
	}

	@Override
	@Transactional
	public JSONObject updateHttWithdrawProveHisList(List<HttWithdrawHisVo> httWithdrawHisVoList, String curPhoneNum,
			String status, SessionInfo sessionInfo, String id) {
		//1. 处理提现记录
		for (HttWithdrawHisVo httWithdrawHisVo : httWithdrawHisVoList) {
			httWithdrawHisVo
					.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			httWithdrawHisVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			if (status.equals("1")) {
				httWithdrawHisVo.setStatusType(6);
				httWithdrawHisVo.setStatus("审核成功");
				
			} else {
				httWithdrawHisVo.setStatusType(7);
				httWithdrawHisVo.setStatus("审核失败");
				if(Integer.valueOf(status) == 3) {
					// 拉黑处理
					HttAppUser httAppUser = httAppUserService.getById(curPhoneNum);
					if(httAppUser.getBlackList() == 0) {
						httAppUser.setBlackList(1);
						httAppUser.setUpdateDate(DateUtil2.getCurrentLongDateTime());
						boolean flag = this.httAppUserService.edit(httAppUser);
						logger.info("phoneNum=" + curPhoneNum + "，拉黑处理结果flag=" + flag);
					}
				}
			}
		}
		//2. 批量更新提现记录
		boolean flag = this.httWithdrawHisService.batchUpdate(httWithdrawHisVoList);
		logger.info("phoneNum=" + curPhoneNum + "，批量更新提现操作结果flag=" + flag);
		if (flag) {
			//3. 处理审核记录
			HttWithdrawProveHis httWithdrawProveHis  = new HttWithdrawProveHis();
			httWithdrawProveHis.setId(id);
			httWithdrawProveHis.setProveResult(Integer.valueOf(status));
			if(Integer.valueOf(status) == 1) {
				httWithdrawProveHis.setProveDesc("通过");
			}else if(Integer.valueOf(status) == 2) {
				httWithdrawProveHis.setProveDesc("拒绝");
			}else if(Integer.valueOf(status) == 3) {
				httWithdrawProveHis.setProveDesc("拒绝并加入黑名单");
			}
			httWithdrawProveHis.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			flag = this.httWithdrawProveHisDao.updateById(httWithdrawProveHis);
			logger.info("phoneNum=" + curPhoneNum + "，处理审核记录操作结果flag=" + flag);
			Map<String, Object> map = new HashMap<>();
			if (flag) {
				map.put("result", "成功");
				map.put("success", "0");
				return JSONObject.fromObject(map);
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("result", "失败");
		map.put("success", "1");
		return JSONObject.fromObject(map);
	}

	@Override
	public List<String> getIdList(HttWithdrawProveHisSearch httWithdrawProveHisSearch) {
		return this.httWithdrawProveHisDao.getIdList(httWithdrawProveHisSearch);
	}

}
