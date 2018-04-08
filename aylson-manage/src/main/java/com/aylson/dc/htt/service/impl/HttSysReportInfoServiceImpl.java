package com.aylson.dc.htt.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttSysReportInfoDao;
import com.aylson.dc.htt.search.HttSysReportInfoSearch;
import com.aylson.dc.htt.service.HttSysReportInfoService;
import com.aylson.dc.htt.vo.HttSysReportInfoVo;

@Service
public class HttSysReportInfoServiceImpl  extends BaseServiceImpl<HttSysReportInfoVo, HttSysReportInfoSearch> implements HttSysReportInfoService {
	
	@Autowired
	private HttSysReportInfoDao httSysReportInfoDao;
	
	@Override
	protected BaseDao<HttSysReportInfoVo, HttSysReportInfoSearch> getBaseDao() {
		return httSysReportInfoDao;
	}

	/**
	 * 当日新增用户数
	 */
	public HttSysReportInfoVo selectNewUserOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectNewUserOfDay(params);
	}
	
	/**
	 * 用户金币余额
	 */
	public HttSysReportInfoVo selectUserGoldOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectUserGoldOfDay(params);
	}
	
	/**
	 * 用户零钱金额
	 */
	public HttSysReportInfoVo selectUserBalanceOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectUserBalanceOfDay(params);
	}
	
	/**
	 * 用户提现金额
	 */
	public HttSysReportInfoVo selectUserWithdrawOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectUserWithdrawOfDay(params);
	}
	
	/**
	 * 用户浏览新闻数
	 */
	public HttSysReportInfoVo selectUserAllRead(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectUserAllRead(params);
	}
	
	/**
	 * 用户总金币余额
	 */
	public HttSysReportInfoVo selectAllGold() {
		return this.httSysReportInfoDao.selectAllGold();
	}
	
	/**
	 * 用户总零钱余额
	 */
	public HttSysReportInfoVo selectAllBalance() {
		return this.httSysReportInfoDao.selectAllBalance();
	}
}
