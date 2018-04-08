package com.aylson.dc.htt.vo;

import com.aylson.dc.htt.po.HttWithdrawProveHis;

public class HttWithdrawProveHisVo extends HttWithdrawProveHis {

	private static final long serialVersionUID = 467478942491111207L;

	private String totalPage;
	private String curPage;

	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

}
