package com.aylson.dc.htt.dao;

import java.util.Map;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.dc.htt.search.HttSysReportInfoSearch;
import com.aylson.dc.htt.vo.HttSysReportInfoVo;

public interface HttSysReportInfoDao extends BaseDao<HttSysReportInfoVo, HttSysReportInfoSearch> {

	/**
	 * 当日新增用户数
	 */
	public HttSysReportInfoVo selectNewUserOfDay(Map<String, Object> params);
	
	/**
	 * 用户金币余额
	 */
	public HttSysReportInfoVo selectUserGoldOfDay(Map<String, Object> params);
	
	/**
	 * 用户零钱金额
	 */
	public HttSysReportInfoVo selectUserBalanceOfDay(Map<String, Object> params);
	
	/**
	 * 用户提现金额
	 */
	public HttSysReportInfoVo selectUserWithdrawOfDay(Map<String, Object> params);
	
	/**
	 * 用户浏览新闻数
	 */
	public HttSysReportInfoVo selectUserAllRead(Map<String, Object> params);
	
	/**
	 * 用户总金币余额
	 */
	public HttSysReportInfoVo selectAllGold();
	
	/**
	 * 用户总零钱余额
	 */
	public HttSysReportInfoVo selectAllBalance();
}
