package com.aylson.dc.sys.common;

import com.aylson.dc.sys.vo.RoleVo;
import com.aylson.dc.sys.vo.UserVo;


/**
 * session信息模型
 * 
 * 
 */
public class SessionInfo implements java.io.Serializable{

	private static final long serialVersionUID = -6690193385496453902L;
	
	private UserVo user;
	private RoleVo role;
	private Boolean isLiveMode;

	public UserVo getUser() {
		return user;
	}
	public void setUser(UserVo User) {
		this.user = User;
	}
	
	public RoleVo getRole() {
		return role;
	}
	public void setRole(RoleVo role) {
		this.role = role;
	}
	
	public Boolean getIsLiveMode() {
		return isLiveMode;
	}
	public void setIsLiveMode(Boolean isLiveMode) {
		this.isLiveMode = isLiveMode;
	}
	

}
