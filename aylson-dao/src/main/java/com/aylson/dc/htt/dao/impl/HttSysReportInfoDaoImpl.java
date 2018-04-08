package com.aylson.dc.htt.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.HttSysReportInfoDao;
import com.aylson.dc.htt.search.HttSysReportInfoSearch;
import com.aylson.dc.htt.vo.HttSysReportInfoVo;

@Repository
public class HttSysReportInfoDaoImpl extends BaseDaoImpl<HttSysReportInfoVo, HttSysReportInfoSearch> implements HttSysReportInfoDao {

	@Override
	public HttSysReportInfoVo selectNewUserOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectNewUserOfDay"), params);
	}

	@Override
	public HttSysReportInfoVo selectUserGoldOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectUserGoldOfDay"), params);
	}

	@Override
	public HttSysReportInfoVo selectUserBalanceOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectUserBalanceOfDay"), params);
	}

	@Override
	public HttSysReportInfoVo selectUserWithdrawOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectUserWithdrawOfDay"), params);
	}

	@Override
	public HttSysReportInfoVo selectUserAllRead(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectUserAllRead"), params);
	}
	
	@Override
	public HttSysReportInfoVo selectAllGold() {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectAllGold"));
	}
	
	@Override
	public HttSysReportInfoVo selectAllBalance() {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectAllBalance"));
	}

}
