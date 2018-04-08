package com.aylson.dc.htt.search;

import com.aylson.core.frame.search.BaseSearch;

public class HttWithdrawProveUserLinkSearch extends BaseSearch {

	private static final long serialVersionUID = -2219868499799737769L;

	// 匹配查询
	private String phoneNum; // 手机号码
	private String pid; // 关联id

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
}