package com.aylson.dc.htt.search;

import com.aylson.core.frame.search.BaseSearch;

public class HttAppUserSearch extends BaseSearch {

	private static final long serialVersionUID = -2219868499799737769L;

	// 精准匹配
	private Integer isDaka; // 是否是大咖账户
	private String inviteCode; // 邀请码
	private String phoneNumLike; // 全局身份唯一ID
	private String realPhoneNum;// 手机号码

	private Integer isOrderByStudents;// 是否按徒弟数量排序
	private String students;// 徒弟数
	private String unUseDay;// 未使用天数
	private String unUseDate;// 未使用天数对应的日期
	private Integer isBindRealPhone;// 是否绑定手机号码
	private String gold;// 剩余金币数

	// 模糊查询
	private String userNameLike; // 姓名

	public String getRealPhoneNum() {
		return realPhoneNum;
	}

	public void setRealPhoneNum(String realPhoneNum) {
		this.realPhoneNum = realPhoneNum;
	}

	public String getUserNameLike() {
		return userNameLike;
	}

	public void setUserNameLike(String userNameLike) {
		this.userNameLike = userNameLike;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Integer getIsDaka() {
		return isDaka;
	}

	public void setIsDaka(Integer isDaka) {
		this.isDaka = isDaka;
	}

	public String getPhoneNumLike() {
		return phoneNumLike;
	}

	public void setPhoneNumLike(String phoneNumLike) {
		this.phoneNumLike = phoneNumLike;
	}

	public Integer getIsOrderByStudents() {
		return isOrderByStudents;
	}

	public void setIsOrderByStudents(Integer isOrderByStudents) {
		this.isOrderByStudents = isOrderByStudents;
	}

	public String getStudents() {
		return students;
	}

	public void setStudents(String students) {
		this.students = students;
	}

	public String getUnUseDay() {
		return unUseDay;
	}

	public void setUnUseDay(String unUseDay) {
		this.unUseDay = unUseDay;
	}

	public String getUnUseDate() {
		return unUseDate;
	}

	public void setUnUseDate(String unUseDate) {
		this.unUseDate = unUseDate;
	}

	public Integer getIsBindRealPhone() {
		return isBindRealPhone;
	}

	public void setIsBindRealPhone(Integer isBindRealPhone) {
		this.isBindRealPhone = isBindRealPhone;
	}

	public String getGold() {
		return gold;
	}

	public void setGold(String gold) {
		this.gold = gold;
	}

}
