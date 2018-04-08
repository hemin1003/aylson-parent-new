package com.aylson.dc.htt.search;

import com.aylson.core.frame.search.BaseSearch;

public class HttUserInfoSearch  extends BaseSearch{

	private static final long serialVersionUID = -2219868499799737769L;
	
	private Integer num;
	private Integer type;
	private String currentTime;
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
}
