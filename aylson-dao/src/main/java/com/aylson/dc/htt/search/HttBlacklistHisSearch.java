package com.aylson.dc.htt.search;

import com.aylson.core.frame.search.BaseSearch;

public class HttBlacklistHisSearch extends BaseSearch {

	private static final long serialVersionUID = -2219868499799737769L;

	// 匹配查询
	private String phoneNum;// 手机号码
	private String source;// 命中来源
	private String accountStatus;// 账号状态
	private String createDate;// 创建时间
	private String tomorrowDate;

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getTomorrowDate() {
		return tomorrowDate;
	}

	public void setTomorrowDate(String tomorrowDate) {
		this.tomorrowDate = tomorrowDate;
	}

}
