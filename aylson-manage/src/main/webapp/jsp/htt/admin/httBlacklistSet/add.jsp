<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style>
.param_th{
text-align:center!important;
font-size:14px;
padding:5px;
}
.param_td_oper{
	text-align:center!important;
}
.imgItem_td_1{
	width:60px;
	height:100px;
	padding:2px;
	margin:2px;
}
.imgItem_td_1 img{
	width:60px;
}
.basic_td{
text-align:left!important;
}
</style>

<div align="center" >
	<div class="easyui-tabs" id="tabActivity" style="width:100%">
		 <div title="黑名单规则" style="padding:10px;text-align:center">
		 	<form id="httBlacklistSetConfigForm" method="post">
				<table class="tableForm" style="width:99%;">
					
					<tr>
						<th>字段名：</th>
						<td colspan="3" style="text-align:left">
							<select id="columnNameFlag" name="columnNameFlag" class="easyui-combobox" " 
								data-options=" 
								onSelect:function(data){
									$('#columnName').val(data.value);
								}" 
								style="width:95%; " editable=false>
								<option value="deviceName">设备名</option>
								<option value="imei">IMEI码</option>
								<option value="ip">应用IP地址</option>
								<option value="mac">Mac地址</option>
								<option value="wifi">Wifi连接名</option>
								<option value="installedList">安装列表md5</option>
								<option value="sIp">公网登录IP</option>
								<option value="wifiMac">Wifi mac地址</option>
								<option value="longitude/latitude">经纬度</option>
								<option value="position">位置md5</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>字段说明：</th>
						<td colspan="3" style="text-align:left">
							<input name="columnDesc" value="${httBlacklistSetVo.columnDesc}" data-options="required:false"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>匹配值：</th>
						<td colspan="3" style="text-align:left">
							<input name="columnValue" value="${httBlacklistSetVo.columnValue}" data-options="required:false"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>是否拉黑处理：</th>
						<td colspan="3" style="text-align:left">
							<select id="isBlackFlag" name="isBlackFlag" class="easyui-combobox" " 
								data-options=" 
								onSelect:function(data){
									$('#isBlack').val(data.value);
								}" 
								style="width:95%; " editable=false>
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>
					</tr>
					
				</table>
				<input name="id" type="hidden" value="${httBlacklistSetVo.id}"/>
				<input name="isBlack" id="isBlack" value="${httBlacklistSetVo.isBlack}" type="hidden"/>
				<input name="columnName" id="columnName" value="${httBlacklistSetVo.columnName}" type="hidden"/>
			</form>
		</div>
	</div> 
</div>

<script>
$(function(){
	var select = document.getElementById("isBlackFlag");  
	var value = $('#isBlack').val();
	var isFlag = 0;
	for(var i=0; i<select.options.length; i++){  
	    if(select.options[i].value == value){
	        select.options[i].selected = true;
	        isFlag = 1;
	        break;
	    }
	}
	if(isFlag == 0){
		$('#isBlack').val(1);
	}
	
	var select = document.getElementById("columnNameFlag");  
	var value = $('#columnName').val();
	var isFlag1 = 0;
	for(var i=0; i<select.options.length; i++){  
	    if(select.options[i].value == value){
	        select.options[i].selected = true;
	        isFlag1 = 1;
	        break;
	    }
	}
	if(isFlag1 == 0){
		$('#columnName').val(1);
	}
});
</script>
