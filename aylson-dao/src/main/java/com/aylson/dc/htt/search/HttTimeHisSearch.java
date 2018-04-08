package com.aylson.dc.htt.search;

import com.aylson.core.frame.search.BaseSearch;

public class HttTimeHisSearch extends BaseSearch {

	private static final long serialVersionUID = -2219868499799737769L;

	// 匹配查询
	private String phoneNum;// 全局身份唯一ID

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

}
