package com.aylson.dc.htt.po;

import java.io.Serializable;

public class HttAppUser implements Serializable {

	private static final long serialVersionUID = -771302935941259817L;

	private String phoneNum; // 手机号码
	private String userPwd; // 密码
	private String gold; // 金币余额
	private String balance; // 零钱余额
	private String userName; // 姓名
	private String address; // 住址
	private String wechat; // 微信号
	private String qq; // QQ号
	private String email; // 邮箱地址
	private String registerDate; // 注册时间
	private String lastLoginDate; // 最后一次登录时间
	private String updateDate;

	private Integer blackList; // 是否黑名单用户
	private Integer shareCount; // 已分享次数
	private Integer firstShare; // 首次分享标识
	private Integer firstInvite; // 首次邀请成功标识
	private Integer firstRead; // 首次阅读标识
	private Integer students; // 徒弟数
	private Integer dailyCheckIn; // 今日签到标识

	private Integer continueCheckIn; // 连续签到标识
	private String inviteCode; // 邀请码
	private Integer isMaster; // 是否已有师傅标识
	private Integer isMasterFinished; // 师傅分步奖励全部完成标识
	private Integer isInviteStep; // 每次分步邀请奖励标识
	private Integer accountStatus; // 账户状态

	private Integer isBindWechat; // 是否已绑定微信
	private Integer isBindPhoneNum; // 是否已绑定手机号码
	private Integer isBindAlipay; // 是否已绑定支付宝
	private Integer isBindTelPhoneNum; // 是否绑定话费号码
	private String realPhoneNum; // 微信登录后绑定的手机号码
	private Integer isDaka; // 是否是大咖账户
	private String registerIp; // 注册IP

	private String imei;// IMEI码
	private String msgCode;// 注册短信码

	private String totalGold;// 总收益金币
	private String studentsGold;// 徒弟贡献总收益金币
	
	private String blackRuleDesc;	//拉黑匹配策略说明
	private Integer isDailyVideo;	//日常任务-五分钟视频任务标识
	private String versionCode;		//版本号

	public String getBlackRuleDesc() {
		return blackRuleDesc;
	}

	public void setBlackRuleDesc(String blackRuleDesc) {
		this.blackRuleDesc = blackRuleDesc;
	}

	public Integer getIsDailyVideo() {
		return isDailyVideo;
	}

	public void setIsDailyVideo(Integer isDailyVideo) {
		this.isDailyVideo = isDailyVideo;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getTotalGold() {
		return totalGold;
	}

	public void setTotalGold(String totalGold) {
		this.totalGold = totalGold;
	}

	public String getStudentsGold() {
		return studentsGold;
	}

	public void setStudentsGold(String studentsGold) {
		this.studentsGold = studentsGold;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	private String channel;// APP来源渠道

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public Integer getIsBindWechat() {
		return isBindWechat;
	}

	public void setIsBindWechat(Integer isBindWechat) {
		this.isBindWechat = isBindWechat;
	}

	public Integer getIsBindPhoneNum() {
		return isBindPhoneNum;
	}

	public void setIsBindPhoneNum(Integer isBindPhoneNum) {
		this.isBindPhoneNum = isBindPhoneNum;
	}

	public Integer getIsBindAlipay() {
		return isBindAlipay;
	}

	public void setIsBindAlipay(Integer isBindAlipay) {
		this.isBindAlipay = isBindAlipay;
	}

	public Integer getIsBindTelPhoneNum() {
		return isBindTelPhoneNum;
	}

	public void setIsBindTelPhoneNum(Integer isBindTelPhoneNum) {
		this.isBindTelPhoneNum = isBindTelPhoneNum;
	}

	public String getRealPhoneNum() {
		return realPhoneNum;
	}

	public void setRealPhoneNum(String realPhoneNum) {
		this.realPhoneNum = realPhoneNum;
	}

	public Integer getIsDaka() {
		return isDaka;
	}

	public void setIsDaka(Integer isDaka) {
		this.isDaka = isDaka;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Integer getContinueCheckIn() {
		return continueCheckIn;
	}

	public void setContinueCheckIn(Integer continueCheckIn) {
		this.continueCheckIn = continueCheckIn;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Integer getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(Integer isMaster) {
		this.isMaster = isMaster;
	}

	public Integer getIsMasterFinished() {
		return isMasterFinished;
	}

	public void setIsMasterFinished(Integer isMasterFinished) {
		this.isMasterFinished = isMasterFinished;
	}

	public Integer getIsInviteStep() {
		return isInviteStep;
	}

	public void setIsInviteStep(Integer isInviteStep) {
		this.isInviteStep = isInviteStep;
	}

	public Integer getBlackList() {
		return blackList;
	}

	public void setBlackList(Integer blackList) {
		this.blackList = blackList;
	}

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public Integer getFirstShare() {
		return firstShare;
	}

	public void setFirstShare(Integer firstShare) {
		this.firstShare = firstShare;
	}

	public Integer getFirstInvite() {
		return firstInvite;
	}

	public void setFirstInvite(Integer firstInvite) {
		this.firstInvite = firstInvite;
	}

	public Integer getFirstRead() {
		return firstRead;
	}

	public void setFirstRead(Integer firstRead) {
		this.firstRead = firstRead;
	}

	public Integer getStudents() {
		return students;
	}

	public void setStudents(Integer students) {
		this.students = students;
	}

	public Integer getDailyCheckIn() {
		return dailyCheckIn;
	}

	public void setDailyCheckIn(Integer dailyCheckIn) {
		this.dailyCheckIn = dailyCheckIn;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getGold() {
		return gold;
	}

	public void setGold(String gold) {
		this.gold = gold;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
}
