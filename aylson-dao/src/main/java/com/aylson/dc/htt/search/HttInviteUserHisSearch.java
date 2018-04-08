package com.aylson.dc.htt.search;

import com.aylson.core.frame.search.BaseSearch;

public class HttInviteUserHisSearch  extends BaseSearch{

	private static final long serialVersionUID = -2219868499799737769L;
	
	//模糊查询
	private String masterPhoneNumLike;	//师傅手机号码
	private String studentPhoneNumLike;	//徒弟手机号码
	
	//精确查询
	private String masterRegisterIp; // 师傅注册IP
	private String studentRegisterIp; //徒弟注册IP
	private String createDate;//邀请时间
	private String tomorrowDate;
	private Integer registerType ;//注册类型

	public Integer getRegisterType() {
		return registerType;
	}
	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
	}
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
	public String getMasterRegisterIp() {
		return masterRegisterIp;
	}
	public void setMasterRegisterIp(String masterRegisterIp) {
		this.masterRegisterIp = masterRegisterIp;
	}
	public String getStudentRegisterIp() {
		return studentRegisterIp;
	}
	public void setStudentRegisterIp(String studentRegisterIp) {
		this.studentRegisterIp = studentRegisterIp;
	}
	public String getMasterPhoneNumLike() {
		return masterPhoneNumLike;
	}
	public void setMasterPhoneNumLike(String masterPhoneNumLike) {
		this.masterPhoneNumLike = masterPhoneNumLike;
	}
	public String getStudentPhoneNumLike() {
		return studentPhoneNumLike;
	}
	public void setStudentPhoneNumLike(String studentPhoneNumLike) {
		this.studentPhoneNumLike = studentPhoneNumLike;
	}
	
}
