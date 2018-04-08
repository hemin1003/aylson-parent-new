package com.aylson.dc.htt.service;

import javax.servlet.http.HttpServletRequest;

import com.aylson.core.frame.service.BaseService;
import com.aylson.dc.htt.po.BigUser;
import com.aylson.dc.htt.search.BigUserSearch;
import com.aylson.dc.htt.vo.BigUserVo;

public interface BigUserService  extends BaseService<BigUser, BigUserSearch> {
	
	/**
	 * 添加用户
	 */
	public int addUser(BigUserVo bigUserVo, HttpServletRequest request);
}
