package com.aylson.dc.htt.po;

import java.io.Serializable;

public class HttSpiderConfig implements Serializable{
	
	private static final long serialVersionUID = 3461505427564992123L;
	
	private String id;			//主键
	private String domain;		//域名值
	private String domainName;	//域名名称
	private Integer alpha;		//报警阀值
	private Integer reqCount;	//并发请求数
	private Integer threadCount;	//并发处理线程数
	private String createDate;	//创建时间
	private String createdBy;	//创建人
	private String updateDate;	//更新时间
	private String updatedBy;	//更新人
	
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
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
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Integer getAlpha() {
		return alpha;
	}
	public void setAlpha(Integer alpha) {
		this.alpha = alpha;
	}
	public Integer getReqCount() {
		return reqCount;
	}
	public void setReqCount(Integer reqCount) {
		this.reqCount = reqCount;
	}
	public Integer getThreadCount() {
		return threadCount;
	}
	public void setThreadCount(Integer threadCount) {
		this.threadCount = threadCount;
	}
	@Override
	public String toString() {
		return "SpiderConfig [id=" + id + ", domain=" + domain + ", domainName=" + domainName + ", alpha=" + alpha
				+ ", reqCount=" + reqCount + ", threadCount=" + threadCount + ", createDate=" + createDate
				+ ", createdBy=" + createdBy + ", updateDate=" + updateDate + ", updatedBy=" + updatedBy + "]";
	}
}
