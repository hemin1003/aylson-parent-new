package com.aylson.dc.htt.search;

import com.aylson.core.frame.search.BaseSearch;

public class HttAwardHisChildSearch extends BaseSearch {

	private static final long serialVersionUID = -2219868499799737769L;

	// 匹配查询
	private String phoneNumLike; // 手机号码
	private Integer awardType; // 奖励类型
	private String createDate;// 记录日期
	private String tomorrowDate;//记录日期的下一天

	public String getTomorrowDate() {
		return tomorrowDate;
	}

	public void setTomorrowDate(String tomorrowDate) {
		this.tomorrowDate = tomorrowDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getAwardType() {
		return awardType;
	}

	public void setAwardType(Integer awardType) {
		this.awardType = awardType;
	}

	public String getPhoneNumLike() {
		return phoneNumLike;
	}

	public void setPhoneNumLike(String phoneNumLike) {
		this.phoneNumLike = phoneNumLike;
	}
}
