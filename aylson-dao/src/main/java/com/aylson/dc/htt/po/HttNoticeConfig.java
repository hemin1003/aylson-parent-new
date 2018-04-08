package com.aylson.dc.htt.po;

import java.io.Serializable;

public class HttNoticeConfig implements Serializable {

	private static final long serialVersionUID = -1537362088309338396L;

	private String id; // 主键
	private String title;// 标题
	private String description;// 描述
	private String action;// 动态参数
	private String tag;//标签
	private String createDate;// 创建时间
	private String createdBy;// 创建人
	private String updateDate;// 更新时间
	private String updatedBy;// 更新人

	private String titleMapUrl;// 标题图
	private String buttonCopywriting;// 按钮文案
	private String notificationType;// 通知类型

	private Integer status;// 记录状态，1=已下线，2=已上线

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getTitleMapUrl() {
		return titleMapUrl;
	}

	public void setTitleMapUrl(String titleMapUrl) {
		this.titleMapUrl = titleMapUrl;
	}

	public String getButtonCopywriting() {
		return buttonCopywriting;
	}

	public void setButtonCopywriting(String buttonCopywriting) {
		this.buttonCopywriting = buttonCopywriting;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	

}
