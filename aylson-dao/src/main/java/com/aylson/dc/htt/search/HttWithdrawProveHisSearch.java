package com.aylson.dc.htt.search;

import com.aylson.core.frame.search.BaseSearch;

public class HttWithdrawProveHisSearch extends BaseSearch {

	private static final long serialVersionUID = 7258302646362351840L;

	// 匹配查询
	private String startDate;
	private String endDate;
	private String phoneNum;
	private Integer accountStatus;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

}
