package com.aylson.dc.htt.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.SysUserReportInfoDao;
import com.aylson.dc.htt.search.SysUserReportInfoSearch;
import com.aylson.dc.htt.service.SysUserReportInfoService;
import com.aylson.dc.htt.vo.SysUserReportInfoVo;

@Service
public class SysUserReportInfoServiceImpl  extends BaseServiceImpl<SysUserReportInfoVo, SysUserReportInfoSearch> implements SysUserReportInfoService {
	
	@Autowired
	private SysUserReportInfoDao httSysReportInfoDao;
	
	@Override
	protected BaseDao<SysUserReportInfoVo, SysUserReportInfoSearch> getBaseDao() {
		return httSysReportInfoDao;
	}

	/**
	 * 用户总数
	 */
	@Override
	public SysUserReportInfoVo selectAllUser() {
		return this.httSysReportInfoDao.selectAllUser();
	}
	
	/**
	 * 当日新增用户数
	 */
	@Override
	public SysUserReportInfoVo selectNewUserOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectNewUserOfDay(params);
	}
	
	/**
	 * 自邀请新增
	 */
	@Override
	public SysUserReportInfoVo selectInviteUserOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectInviteUserOfDay(params);
	}
	
	/**
	 * 阅读文章数
	 */
	@Override
	public SysUserReportInfoVo selectArticleCountOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectArticleCountOfDay(params);
	}
	
	/**
	 * 文章阅读次数
	 */
	@Override
	public SysUserReportInfoVo selectReadCountOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectReadCountOfDay(params);
	}
}
