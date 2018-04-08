package com.aylson.dc.htt.search;

import com.aylson.core.frame.search.BaseSearch;

public class HttNoticeConfigSearch extends BaseSearch {

	private static final long serialVersionUID = -2219868499799737769L;

	// 模糊查询
	private String titleLike;// 公告标题

	public String getTitleLike() {
		return titleLike;
	}

	public void setTitleLike(String titleLike) {
		this.titleLike = titleLike;
	}

}
