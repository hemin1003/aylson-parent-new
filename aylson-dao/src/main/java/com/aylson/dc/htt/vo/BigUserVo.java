package com.aylson.dc.htt.vo;

import com.aylson.dc.htt.po.BigUser;

public class BigUserVo extends BigUser {

	private static final long serialVersionUID = -2967673263678900226L;

	private String userPwd;

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
}
