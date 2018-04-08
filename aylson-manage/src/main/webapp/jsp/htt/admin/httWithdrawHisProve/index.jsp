<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/resources/inc/meta.jsp"></jsp:include>
<jsp:include page="/resources/inc/easyui.jsp"></jsp:include>
</head>
<style>
a:link {
	color: black
} /* 未被访问的链接     蓝色 */
a:visited {
	color: black
} /* 已被访问过的链接   蓝色 */
a:hover {
	color: red
} /* 鼠标悬浮在上的链接 蓝色 */
a:active {
	color: red
} /* 鼠标点中激活链接   蓝色 */
</style>
<body class="easyui-layout" fit="true">
	<div region="north"
		style="white-space: nowrap; padding: 5px; height: 50px;">
		<div style="margin-bottom: 5px">
			<form id="httWithdrawHisProveForm" method="post">
				<table class="table_content" border="0">
					<tr>
						<td class="tal"><input class="easyui-datebox"
							name="startDate" id="startDate" /> 至</td>
						<td class="tal"><input class="easyui-datebox" name="endDate"
							id="endDate"/></td>
						<td class="tal"><a href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'" onclick="doSearch()">查询</a>
						</td>
						<td class="tal"><input type="checkbox" name="isSuspect"
							id="isSuspect">只查看疑似用户</input></td>
						<td style="padding-left: 20px"><a href="#"
							class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
							onclick="doPass()">通过</a> &nbsp;&nbsp; <a href="#"
							class="easyui-linkbutton" data-options="iconCls:'icon-no'" onclick="doReject()">拒绝</a>
							&nbsp;&nbsp; <a href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-cancel'" onclick="doRejectAndBlackList()">拒绝并加入黑名单</a></td>
						<td style="padding-left: 20px"><a href="#"
							onclick="clickLast()">《上一个</a> &nbsp; &nbsp; <lable
								id="currentPage">0</lable> &nbsp;<lable>/</lable> &nbsp;<lable
								id="totalPage">0</lable> &nbsp; &nbsp; <a href="#"
							onclick="clickNext()">下一个》</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center'" border="false"
		style="overflow: auto; width: 85%;">
		<div>
			&nbsp;&nbsp;<strong><font size=3>账号信息</font></strong>
		</div>
		<div>
			<form id="httWithdrawHisDataForm" method="post">
				<table class="tableForm" style="width: 80%;">
					<tr>
						<th>账号：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="phoneNum" id="phoneNum"
							style="width: 190px; text-align: left" /></td>
						<th>密码md5：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="userPwd" id="userPwd"
							style="width: 190px; text-align: left" /></td>
						<th>同密码md5账号数：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="userPwdCount" id="userPwdCount"
							style="width: 190px; text-align: left" /></td>
					</tr>
					<tr>
						<th>渠道：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="channel" id="channel"
							style="width: 190px; text-align: left" /></td>
						<th>设备名称：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="deviceName" id="deviceName"
							style="width: 190px; text-align: left" /></td>
						<th>同设备名账号数：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="deviceNameCount"
							id="deviceNameCount" style="width: 190px; text-align: left" /></td>
					</tr>
					<tr>
						<th>微信名称：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="wechatNickname" id="wechatNickname"
							style="width: 190px; text-align: left" /></td>
						<th>注册IP：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="registerIp" id="registerIp"
							style="width: 190px; text-align: left" /></td>
						<th>同注册IP账号数：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="registerIpCount"
							id="registerIpCount" style="width: 190px; text-align: left" /></td>
					</tr>
					<tr>
						<th>手机号：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="ownPhoneNum" id="ownPhoneNum"
							style="width: 190px; text-align: left" /></td>
						<th>内网IP：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="ip" id="ip"
							style="width: 190px; text-align: left" /></td>
						<th>同内网IP账号数：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="ipCount" id="ipCount"
							style="width: 190px; text-align: left" /></td>
					</tr>
					<tr>
						<th>支付宝账号：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="alipayAccount" id="alipayAccount"
							style="width: 190px; text-align: left" /></td>
						<th>经纬度：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="coordinate" id="coordinate"
							style="width: 190px; text-align: left" /></td>
						<th>同经纬度账号数：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="coordinateCount"
							id="coordinateCount" style="width: 190px; text-align: left" /></td>
					</tr>
					<tr>
						<th>imei：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="imei" id="imei"
							style="width: 190px; text-align: left" /></td>
						<th>地理位置：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="location" id="location"
							style="width: 190px; text-align: left" /></td>
						<th>同地理位置账号数：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="locationCount" id="locationCount"
							style="width: 190px; text-align: left" /></td>
					</tr>
					<tr>
						<th>注册时间：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="registerDate" id="registerDate"
							style="width: 190px; text-align: left" /></td>
						<th>wifi名称（wifimac）：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="wifiMac" id="wifiMac"
							style="width: 190px; text-align: left" /></td>
						<th>同wifimac账号数：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="wifiMacCount" id="wifiMacCount"
							style="width: 190px; text-align: left" /></td>
					</tr>
					<tr>
						<th>账号状态：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="accountStatus" id="accountStatus"
							style="width: 190px; text-align: left" /></td>
						<th>安装列表md5：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="installedListMdCode"
							id="installedListMdCode" style="width: 190px; text-align: left" />
						</td>
						<th>同安装列表账号数：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="installedListMdCodeCount"
							id="installedListMdCodeCount"
							style="width: 190px; text-align: left" /></td>
					</tr>
					<tr>
						<th>是否安装作弊器app：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="isCheatedApp" id="isCheatedApp"
							style="width: 190px; text-align: left" /></td>
						<th>师傅账号：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="masterPhoneNum" id="masterPhoneNum"
							style="width: 190px; text-align: left" /></td>
						<th>徒弟数：</th>
						<td colspan="3" style="text-align: left"><input
							class="easyui-textbox" name="students" id="students"
							style="width: 190px; text-align: left" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div>
			&nbsp;&nbsp;<strong><font size=3>订单记录</font></strong>
		</div>
		<div style="width: 100%; height: 40%;">
			<table id="datagrid"></table>
		</div>
		<div>
			&nbsp;&nbsp;<strong><font size=3>关联账号</font></strong>
		</div>
		<div style="width: 100%; height: 40%;">
			<table id="datagrid2"></table>
		</div>
		<div>
			&nbsp;&nbsp;<strong><font size=3>黑名单规则历史查询</font></strong>
		</div>
		<div style="width: 100%; height: 40%;">
			<table id="datagrid3"></table>
		</div>
	</div>
</body>
<script type="text/javascript" charset="UTF-8"
	src="<%=request.getContextPath()%>/resources/js/aylson/htt/httWithdrawHisProve.js?date=2016112516"></script>

<script type="text/javascript">
	$(function(){
	   var curr_time = new Date();
	   var strDate = curr_time.getFullYear()+"-";
	   strDate += curr_time.getMonth()+1+"-";
	   strDate += curr_time.getDate()+"-";
	   strDate += curr_time.getHours()+":";
	   strDate += curr_time.getMinutes()+":";
	   strDate += curr_time.getSeconds();
	   $("#startDate").datebox("setValue", strDate); 
	   $("#endDate").datebox("setValue", strDate);
	});
</script>
</html>