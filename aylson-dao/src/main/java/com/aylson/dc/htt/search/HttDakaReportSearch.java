package com.aylson.dc.htt.search;

import com.aylson.core.frame.search.BaseSearch;

public class HttDakaReportSearch extends BaseSearch {

	private static final long serialVersionUID = -2219868499799737769L;

	private String date;// 当前日期
	private String sevenDayAgo;// 七天前的日期
	private Integer isDateSearch;// 是否按日期搜索标识符 0：否 1：是
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSevenDayAgo() {
		return sevenDayAgo;
	}
	public void setSevenDayAgo(String sevenDayAgo) {
		this.sevenDayAgo = sevenDayAgo;
	}
	public Integer getIsDateSearch() {
		return isDateSearch;
	}
	public void setIsDateSearch(Integer isDateSearch) {
		this.isDateSearch = isDateSearch;
	}
	
}
