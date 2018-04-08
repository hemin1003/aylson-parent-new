package com.aylson.dc.htt.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.SysExpenseReportInfoDao;
import com.aylson.dc.htt.search.SysExpenseReportInfoSearch;
import com.aylson.dc.htt.vo.SysExpenseReportInfoVo;

@Repository
public class SysExpenseReportInfoDaoImpl extends BaseDaoImpl<SysExpenseReportInfoVo, SysExpenseReportInfoSearch> implements SysExpenseReportInfoDao {

	@Override
	public SysExpenseReportInfoVo selectAllGold() {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectAllGold"));
	}
	
	@Override
	public SysExpenseReportInfoVo selectUserGoldOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectUserGoldOfDay"), params);
	}
	
	@Override
	public SysExpenseReportInfoVo selectNewUserOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectNewUserOfDay"), params);
	}
	
	@Override
	public SysExpenseReportInfoVo selectAllInviteAwardOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectAllInviteAwardOfDay"), params);
	}
	
	@Override
	public SysExpenseReportInfoVo selectAllReadAwardOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectAllReadAwardOfDay"), params);
	}
	
	@Override
	public SysExpenseReportInfoVo selectAllOtherAwardOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectAllOtherAwardOfDay"), params);
	}

	@Override
	public SysExpenseReportInfoVo selectUserWithdrawOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectUserWithdrawOfDay"), params);
	}
	
	@Override
	public SysExpenseReportInfoVo selectSuccessWithdrawOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectSuccessWithdrawOfDay"), params);
	}

	@Override
	public SysExpenseReportInfoVo selectAllUserWithdraw(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectAllUserWithdraw"), params);
	}

	@Override
	public SysExpenseReportInfoVo selectAllSuccessWithdraw(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectAllSuccessWithdraw"), params);
	}

}
