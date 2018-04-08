package com.aylson.dc.htt.service.impl;

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
import com.aylson.dc.htt.dao.HttAwardHisDao;
import com.aylson.dc.htt.dao.HttWithdrawHisDao;
import com.aylson.dc.htt.po.HttWithdrawHis;
import com.aylson.dc.htt.search.HttWithdrawHisSearch;
import com.aylson.dc.htt.service.HttWithdrawHisService;
import com.aylson.dc.htt.vo.HttAppUserVo;
import com.aylson.dc.htt.vo.HttAwardHisVo;
import com.aylson.dc.htt.vo.HttWithdrawHisVo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.UUIDUtils;

@Service
public class HttWithdrawHisServiceImpl  extends BaseServiceImpl<HttWithdrawHis, HttWithdrawHisSearch> implements HttWithdrawHisService {
	
	protected static final Logger logger = Logger.getLogger(HttWithdrawHisServiceImpl.class);

	@Autowired
	private HttWithdrawHisDao httWithdrawHisDao;
	@Autowired
	private HttAppUserDao httAppUserDao;
	@Autowired
	private HttAwardHisDao httAwardHisDao;

	@Override
	protected BaseDao<HttWithdrawHis, HttWithdrawHisSearch> getBaseDao() {
		return httWithdrawHisDao;
	}

	@Override
	@Transactional
	public Result updateWithdrawHisInfo(HttWithdrawHisVo httWithdrawHisVo) {
		Result result = new Result();
		String cTime = DateUtil2.getCurrentLongDateTime();
		try{
			//更新提现状态
			httWithdrawHisVo.setUpdateDate(cTime);
			
			//2=支付成功；3=充值成功；；
			if(httWithdrawHisVo.getStatusType() == 2 || httWithdrawHisVo.getStatusType() == 3) {
				logger.info("用户金币兑换提现成功，income=" + httWithdrawHisVo.getIncome());
				
			//4=失败则回退用户金额
			}else if(httWithdrawHisVo.getStatusType() == 4) {
				//2. 如果失败，则需要增加用户金币余额
				HttAppUserVo appUserVo = this.httAppUserDao.selectById(httWithdrawHisVo.getPhoneNum());
				if(appUserVo != null) {
					long gold = Math.abs(Integer.valueOf(httWithdrawHisVo.getGold()));
					//金币余额
					long old = Integer.valueOf(appUserVo.getGold());
					long sum = old + gold;
					appUserVo.setGold(String.valueOf(sum));
					appUserVo.setUpdateDate(cTime);
					
					//3. 回退增加金币记录
					HttAwardHisVo awardHisVo = new HttAwardHisVo();
					awardHisVo.setId(UUIDUtils.create());
					awardHisVo.setPhoneNum(httWithdrawHisVo.getPhoneNum());
					awardHisVo.setAwardType(15);
					awardHisVo.setAwardName("金币返还");
					awardHisVo.setGold("+" + Math.abs(Integer.valueOf(httWithdrawHisVo.getGold())));
					awardHisVo.setCreateDate(cTime);
					awardHisVo.setUpdateDate(cTime);
					
					boolean flag2 = this.httAppUserDao.updateById(appUserVo);
					boolean flag3 = this.httAwardHisDao.insert(awardHisVo);
					logger.info("提现回退：phoneNum=" + httWithdrawHisVo.getPhoneNum() + "，原gold=" + old + ", 回退新增金币gold=" + Math.abs(Integer.valueOf(httWithdrawHisVo.getGold())) 
							+ ", 最后金币gold=" + appUserVo.getGold() + "，操作结果flag2=" + flag2 + ", flag3=" + flag3);
				}
				
			}else if(httWithdrawHisVo.getStatusType() == 6 ){	//审核成功
				HttAppUserVo appUserVo = this.httAppUserDao.selectById(httWithdrawHisVo.getPhoneNum());
				if(appUserVo!= null && appUserVo.getAccountStatus() != 0) {
					appUserVo.setAccountStatus(0);	//0=正常; 1=疑似；2=封禁
					appUserVo.setUpdateDate(cTime);
					boolean flag2 = this.httAppUserDao.updateById(appUserVo);
					if(!flag2) {
						logger.error("审核成功时标记用户：phoneNum=" + httWithdrawHisVo.getPhoneNum() + "失败了，操作结果flag2=" + flag2, new RuntimeException("更新失败"));
					}
				}
			}else if(httWithdrawHisVo.getStatusType() == 7 ){	//审核失败
				HttAppUserVo appUserVo = this.httAppUserDao.selectById(httWithdrawHisVo.getPhoneNum());
				appUserVo.setAccountStatus(1);	//0=正常; 1=疑似；2=封禁
				appUserVo.setUpdateDate(cTime);
				boolean flag2 = this.httAppUserDao.updateById(appUserVo);
				logger.info("审核失败，标记用户：phoneNum=" + httWithdrawHisVo.getPhoneNum() + "，操作结果flag2=" + flag2);
			}
			boolean flag = this.httWithdrawHisDao.updateById(httWithdrawHisVo);	//更新提现状态
			if(flag) {
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
			}else {
				logger.info("操作失败");
				result.setError(ResultCode.CODE_STATE_4006, "操作失败");
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}

	@Override
	public List<HttWithdrawHisVo> selectByIdList(List<String> idList) {
		return this.httWithdrawHisDao.selectByIdList(idList);
	}

	@Override
	public Boolean batchUpdateByPhoneNum(List<HttWithdrawHisVo> withdrawHisList) {
		return this.httWithdrawHisDao.batchUpdateByPhoneNum(withdrawHisList);
	}

	@Override
	public List<HttWithdrawHisVo> selectByPhoneNumList(List<String> phoneNumList) {
		return this.httWithdrawHisDao.selectByPhoneNumList(phoneNumList);
	}
}
