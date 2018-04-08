package com.aylson.dc.htt.search;

import com.aylson.core.frame.search.BaseSearch;

public class HttTraceHisSearch extends BaseSearch {

	private static final long serialVersionUID = -2219868499799737769L;

	private String phoneNum;// 全局身份唯一ID
	private String traceId; // 跟踪ID
	private String source;// 当前请求的新闻渠道标识

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

}
