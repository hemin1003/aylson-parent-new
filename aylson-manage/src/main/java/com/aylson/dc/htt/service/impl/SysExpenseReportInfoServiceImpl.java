package com.aylson.dc.htt.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.SysExpenseReportInfoDao;
import com.aylson.dc.htt.search.SysExpenseReportInfoSearch;
import com.aylson.dc.htt.service.SysExpenseReportInfoService;
import com.aylson.dc.htt.vo.SysExpenseReportInfoVo;

@Service
public class SysExpenseReportInfoServiceImpl  extends BaseServiceImpl<SysExpenseReportInfoVo, SysExpenseReportInfoSearch> implements SysExpenseReportInfoService {
	
	@Autowired
	private SysExpenseReportInfoDao httSysReportInfoDao;
	
	@Override
	protected BaseDao<SysExpenseReportInfoVo, SysExpenseReportInfoSearch> getBaseDao() {
		return httSysReportInfoDao;
	}

	/**
	 * 用户总金币
	 */
	@Override
	public SysExpenseReportInfoVo selectAllGold() {
		return this.httSysReportInfoDao.selectAllGold();
	}
	
	/**
	 * 用户金币余额
	 */
	@Override
	public SysExpenseReportInfoVo selectUserGoldOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectUserGoldOfDay(params);
	}

	/**
	 * 当日新增用户数
	 */
	@Override
	public SysExpenseReportInfoVo selectNewUserOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectNewUserOfDay(params);
	}
	
	/**
	 * 全部邀请奖励
	 */
	@Override
	public SysExpenseReportInfoVo selectAllInviteAwardOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectAllInviteAwardOfDay(params);
	}
	
	/**
	 * 全部阅读奖励
	 */
	@Override
	public SysExpenseReportInfoVo selectAllReadAwardOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectAllReadAwardOfDay(params);
	}
	
	/**
	 * 全部其他奖励
	 */
	@Override
	public SysExpenseReportInfoVo selectAllOtherAwardOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectAllOtherAwardOfDay(params);
	}
	
	/**
	 * 申请提现金额
	 */
	@Override
	public SysExpenseReportInfoVo selectUserWithdrawOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectUserWithdrawOfDay(params);
	}
	
	/**
	 * 成功打款金额
	 */
	@Override
	public SysExpenseReportInfoVo selectSuccessWithdrawOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectSuccessWithdrawOfDay(params);
	}

	/**
	 * 预计总支出金额
	 */
	@Override
	public SysExpenseReportInfoVo selectAllUserWithdraw(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectAllUserWithdraw(params);
	}

	/**
	 * 实际已支出总金额
	 */
	@Override
	public SysExpenseReportInfoVo selectAllSuccessWithdraw(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectAllSuccessWithdraw(params);
	}
}
