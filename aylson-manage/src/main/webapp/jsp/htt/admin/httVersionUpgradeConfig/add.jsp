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
		 <div title="版本升级配置" style="padding:10px;text-align:center">
		 	<form id="httVersionUpgradeConfigForm" method="post">
				<table class="tableForm" style="width:99%;">
					
					<tr>
						<th>Build号：</th>
						<td colspan="3" style="text-align:left">
							<input name="versionCode" value="${versionUpgradeConfigVo.versionCode}" data-options="required:true"
								class="easyui-numberbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>版本号：</th>
						<td colspan="3" style="text-align:left">
							<input name="versionName" value="${versionUpgradeConfigVo.versionName}" data-options="required:true"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>定向渠道：</th>
						<td colspan="3" style="text-align:left">
							<input name="channel" value="${versionUpgradeConfigVo.channel}" data-options="required:true"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>下载链接：</th>
						<td colspan="3" style="text-align:left">
							<input name="apkUrl" value="${versionUpgradeConfigVo.apkUrl}" data-options="required:true"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>更新类型：</th>
						<td colspan="3" style="text-align:left">
							<select id="updateTypeFlag" name="updateTypeFlag" class="easyui-combobox" " 
								data-options=" 
								onSelect:function(data){
									$('#updateType').val(data.value);
								}" 
								style="width:95%; " editable=false>
								<option value="1">强制更新</option>
								<option value="2">非强制更新</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>更新描述：</th>
						<td colspan="3" style="text-align:left">
							<input name="description" value="${versionUpgradeConfigVo.description}" data-options="required:true"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
				</table>
				<input name="id" type="hidden" value="${versionUpgradeConfigVo.id}"/>
				<input name="updateType" id="updateType" type="hidden" value="${versionUpgradeConfigVo.updateType}"/>
			</form>
		</div>
	</div> 
</div>

<script>
$(function(){
	var select = document.getElementById("updateTypeFlag");  
	var value = $('#updateType').val();
	var isFlag = 0;
	for(var i=0; i<select.options.length; i++){  
	    if(select.options[i].value == value){
	        select.options[i].selected = true;
	        isFlag = 1;
	        break;
	    }
	}
	if(isFlag == 0){
		$('#updateType').val(1);
	}
});
</script>
