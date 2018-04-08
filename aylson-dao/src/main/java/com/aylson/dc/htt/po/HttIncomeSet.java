package com.aylson.dc.htt.po;

import java.io.Serializable;

public class HttIncomeSet implements Serializable{
	
	private static final long serialVersionUID = 9110302105370862799L;
	
	private String id;			//主键
	private String income;		//提现金额
	private Integer status;		//记录状态，1=已下线，2=已上线
	private Integer saleFlag;	//销售标识，1=下线；2=上线；3=缺货
	private Integer maxNum;		//每日最大数量
	private String goodType;		//商品类别
	private String goodName;		//商品类别名称
	private String gold;			//兑换金币值
	private String createDate;	//创建时间
	private String createdBy;	//创建人
	private String updateDate;	//更新时间
	private String updatedBy;	//更新人
	
	public Integer getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	public String getGoodType() {
		return goodType;
	}
	public void setGoodType(String goodType) {
		this.goodType = goodType;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
	}
	public Integer getSaleFlag() {
		return saleFlag;
	}
	public void setSaleFlag(Integer saleFlag) {
		this.saleFlag = saleFlag;
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
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
