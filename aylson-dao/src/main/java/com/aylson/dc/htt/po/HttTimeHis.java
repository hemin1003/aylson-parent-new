package com.aylson.dc.htt.po;

import java.io.Serializable;

public class HttTimeHis implements Serializable {

	private static final long serialVersionUID = -1537362088309338396L;

	private String id;// 主键
	private String phoneNum;// 全局身份唯一ID
	private String createDate;// 创建时间
	private String createdBy;// 创建人
	private String updateDate;// 更新时间
	private String updatedBy;// 更新人
	private Integer isUsed;// 是否已使用

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

}
