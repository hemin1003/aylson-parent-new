package com.aylson.dc.htt.search;

import com.aylson.core.frame.search.BaseSearch;

public class HttWithdrawHisSearch extends BaseSearch {

	private static final long serialVersionUID = 7258302646362351840L;

	// 匹配查询
	private String id;
	private Integer withdrawType; // 提现类型
	private Integer statusType; // 提现状态标识
	private String[] idsArray; // 主键集合
	private String sysUserId;
	private String withdrawTime; // 提现时间
	private String updateDate;// 完成时间
	private String phoneNum;// 手机号码

	// 模糊查询
	private String nameLike; // 姓名
	private String accountLike; // 账户名
	private String phoneNumLike; // 手机号码
	
	//时间区间
	private String withdrawTimeBeyond;

	public String getWithdrawTimeBeyond() {
		return withdrawTimeBeyond;
	}

	public void setWithdrawTimeBeyond(String withdrawTimeBeyond) {
		this.withdrawTimeBeyond = withdrawTimeBeyond;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWithdrawTime() {
		return withdrawTime;
	}

	public void setWithdrawTime(String withdrawTime) {
		this.withdrawTime = withdrawTime;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String[] getIdsArray() {
		return idsArray;
	}

	public void setIdsArray(String[] idsArray) {
		this.idsArray = idsArray;
	}

	public Integer getWithdrawType() {
		return withdrawType;
	}

	public void setWithdrawType(Integer withdrawType) {
		this.withdrawType = withdrawType;
	}

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public String getAccountLike() {
		return accountLike;
	}

	public void setAccountLike(String accountLike) {
		this.accountLike = accountLike;
	}

	public Integer getStatusType() {
		return statusType;
	}

	public void setStatusType(Integer statusType) {
		this.statusType = statusType;
	}

	public String getPhoneNumLike() {
		return phoneNumLike;
	}

	public void setPhoneNumLike(String phoneNumLike) {
		this.phoneNumLike = phoneNumLike;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}
