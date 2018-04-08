package com.aylson.dc.htt.service;

import java.util.Map;

import com.aylson.core.frame.service.BaseService;
import com.aylson.dc.htt.search.SysUserReportInfoSearch;
import com.aylson.dc.htt.vo.SysUserReportInfoVo;

public interface SysUserReportInfoService  extends BaseService<SysUserReportInfoVo, SysUserReportInfoSearch> {

	/**
	 * 用户总数
	 */
	public SysUserReportInfoVo selectAllUser();
	
	/**
	 * 当日新增用户数
	 */
	public SysUserReportInfoVo selectNewUserOfDay(Map<String, Object> params);
	
	/**
	 * 自邀请新增
	 */
	public SysUserReportInfoVo selectInviteUserOfDay(Map<String, Object> params);
	
	/**
	 * 阅读文章数
	 */
	public SysUserReportInfoVo selectArticleCountOfDay(Map<String, Object> params);
	
	/**
	 * 文章阅读次数
	 */
	public SysUserReportInfoVo selectReadCountOfDay(Map<String, Object> params);
	
}
