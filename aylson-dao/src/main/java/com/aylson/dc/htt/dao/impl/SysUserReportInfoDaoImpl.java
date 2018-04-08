package com.aylson.dc.htt.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.SysUserReportInfoDao;
import com.aylson.dc.htt.search.SysUserReportInfoSearch;
import com.aylson.dc.htt.vo.SysUserReportInfoVo;

@Repository
public class SysUserReportInfoDaoImpl extends BaseDaoImpl<SysUserReportInfoVo, SysUserReportInfoSearch> implements SysUserReportInfoDao {

	@Override
	public SysUserReportInfoVo selectAllUser() {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectAllUser"));
	}
	
	@Override
	public SysUserReportInfoVo selectNewUserOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectNewUserOfDay"), params);
	}
	
	@Override
	public SysUserReportInfoVo selectInviteUserOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectInviteUserOfDay"), params);
	}
	
	@Override
	public SysUserReportInfoVo selectArticleCountOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectArticleCountOfDay"), params);
	}
	
	@Override
	public SysUserReportInfoVo selectReadCountOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectReadCountOfDay"), params);
	}

}
