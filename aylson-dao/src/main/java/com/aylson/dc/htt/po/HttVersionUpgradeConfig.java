package com.aylson.dc.htt.po;

import java.io.Serializable;

public class HttVersionUpgradeConfig implements Serializable {

	private static final long serialVersionUID = -1537362088309338396L;

	private String id; // 主键
	private Integer versionCode;// 版本号
	private String versionName;// 版本名
	private String channel;// 渠道号
	private String apkUrl;// 下载链接
	private Integer updateType;// 更新类型 1：强制更新 2：非强制更新
	private String description;// 更新描述

	private String createDate;// 创建时间
	private String createdBy;// 创建人
	private String updateDate;// 更新时间
	private String updatedBy;// 更新人

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getApkUrl() {
		return apkUrl;
	}

	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}

	public Integer getUpdateType() {
		return updateType;
	}

	public void setUpdateType(Integer updateType) {
		this.updateType = updateType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
