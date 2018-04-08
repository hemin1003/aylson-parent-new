package com.aylson.dc.htt.dao;

import java.util.Map;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.dc.htt.search.SysExpenseReportInfoSearch;
import com.aylson.dc.htt.vo.SysExpenseReportInfoVo;

public interface SysExpenseReportInfoDao extends BaseDao<SysExpenseReportInfoVo, SysExpenseReportInfoSearch> {
	
	/**
	 * 用户总金币
	 */
	public SysExpenseReportInfoVo selectAllGold();
	
	/**
	 * 用户金币余额
	 */
	public SysExpenseReportInfoVo selectUserGoldOfDay(Map<String, Object> params);

	/**
	 * 当日新增用户数
	 */
	public SysExpenseReportInfoVo selectNewUserOfDay(Map<String, Object> params);
	
	/**
	 * 全部邀请奖励
	 */
	public SysExpenseReportInfoVo selectAllInviteAwardOfDay(Map<String, Object> params);
	
	/**
	 * 全部阅读奖励
	 */
	public SysExpenseReportInfoVo selectAllReadAwardOfDay(Map<String, Object> params);
	
	/**
	 * 全部其他奖励
	 */
	public SysExpenseReportInfoVo selectAllOtherAwardOfDay(Map<String, Object> params);
	
	/**
	 * 申请提现金额
	 */
	public SysExpenseReportInfoVo selectUserWithdrawOfDay(Map<String, Object> params);
	
	/**
	 * 成功打款金额
	 */
	public SysExpenseReportInfoVo selectSuccessWithdrawOfDay(Map<String, Object> params);
	
	/**
	 * 预计总支出金额
	 */
	public SysExpenseReportInfoVo selectAllUserWithdraw(Map<String, Object> params);
	
	/**
	 * 实际已支出总金额
	 */
	public SysExpenseReportInfoVo selectAllSuccessWithdraw(Map<String, Object> params);
	
}
