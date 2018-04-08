package com.aylson.dc.htt.po;

import java.io.Serializable;

public class HttBlacklistSet implements Serializable {

	private static final long serialVersionUID = -1537362088309338396L;

	private String id;// id
	private String columnName;// 字段名
	private String columnDesc;// 字段说明
	private String columnValue;// 匹配值
	private Integer isBlack;// 是否拉黑处理
	private Integer status;// 记录状态
	private String createDate;// 创建时间
	private String createdBy;// 创建人
	private String updateDate;// 更新时间
	private String updatedBy;// 更新人

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnDesc() {
		return columnDesc;
	}

	public void setColumnDesc(String columnDesc) {
		this.columnDesc = columnDesc;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	public Integer getIsBlack() {
		return isBlack;
	}

	public void setIsBlack(Integer isBlack) {
		this.isBlack = isBlack;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

}
