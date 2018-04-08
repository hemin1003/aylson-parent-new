package com.aylson.dc.htt.search;

import com.aylson.core.frame.search.BaseSearch;

public class HttLoginHisSearch  extends BaseSearch{

	private static final long serialVersionUID = -6848948717192026817L;

	//匹配查询
	private String phoneNum; //全局身份ID

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
}
