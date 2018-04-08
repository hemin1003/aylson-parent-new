package com.aylson.dc.htt.po;

import java.io.Serializable;

public class HttAppVersion implements Serializable {

	private static final long serialVersionUID = -6755707962582696900L;

	private String id; // 主键
	private String configName; // 名称
	private String configData; // 渠道详细配置
	private String sourceData; // 新闻源相信配置
	private String channelRange;// 渠道流量分配随机范围和概率值
	private String createDate; // 创建时间

	private String createdBy; // 创建人
	private String updateDate; // 更新时间
	private String updatedBy; // 更新人

	public String getChannelRange() {
		return channelRange;
	}

	public void setChannelRange(String channelRange) {
		this.channelRange = channelRange;
	}

	public String getSourceData() {
		return sourceData;
	}

	public void setSourceData(String sourceData) {
		this.sourceData = sourceData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigData() {
		return configData;
	}

	public void setConfigData(String configData) {
		this.configData = configData;
	}
}
