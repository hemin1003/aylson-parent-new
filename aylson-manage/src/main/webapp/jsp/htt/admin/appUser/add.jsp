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
		 <div title="用户详情" style="padding:10px;text-align:center">
		 	<form id="appUserConfigForm" method="post">
				<table class="tableForm" style="width:99%;">
					<tr>
						<th>全局身份唯一ID：</th>
						<td colspan="3" style="text-align:left">
							<input value="${appUserVo.phoneNum}" data-options="required:true"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>手机号码：</th>
						<td colspan="3" style="text-align:left">
							<input value="${appUserVo.realPhoneNum}" data-options="required:true"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>总收益金币：</th>
						<td colspan="3" style="text-align:left">
							<input value="${appUserVo.totalGold}" 
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>徒弟贡献总收益金币：</th>
						<td colspan="3" style="text-align:left">
							<input value="${appUserVo.studentsGold}" 
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>住址：</th>
						<td colspan="3" style="text-align:left">
							<input name="address" value="${appUserVo.address}"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>微信号：</th>
						<td colspan="3" style="text-align:left">
							<input name="wechat" value="${appUserVo.wechat}"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>QQ号：</th>
						<td colspan="3" style="text-align:left">
							<input name="qq" value="${appUserVo.qq}"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>邮箱地址：</th>
						<td colspan="3" style="text-align:left">
							<input name="email" value="${appUserVo.email}"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>是否已绑定师徒关系：</th>
						<td colspan="3" style="text-align:left">
							<select id="isMasterFlag" name="isMasterFlag" class="easyui-combobox" 
								data-options=" 
								onSelect:function(data){
									$('#isMaster').val(data.value);
								}" 
								style="width:50%; " editable=false>
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>是否已绑定微信：</th>
						<td colspan="3" style="text-align:left">
							<select id="isBindWechatFlag" name="isBindWechatFlag" class="easyui-combobox" 
								data-options=" 
								onSelect:function(data){
									$('#isBindWechat').val(data.value);
								}" 
								style="width:50%; " editable=false>
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>是否已绑定手机号码：</th>
						<td colspan="3" style="text-align:left">
							<select id="isBindPhoneNumFlag" name="isBindPhoneNumFlag" class="easyui-combobox" 
								data-options=" 
								onSelect:function(data){
									$('#isBindPhoneNum').val(data.value);
								}" 
								style="width:50%; " editable=false>
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>是否已绑定支付宝：</th>
						<td colspan="3" style="text-align:left">
							<select id="isBindAlipayFlag" name="isBindAlipayFlag" class="easyui-combobox" 
								data-options=" 
								onSelect:function(data){
									$('#isBindAlipay').val(data.value);
								}" 
								style="width:50%; " editable=false>
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>是否绑定话费号码：</th>
						<td colspan="3" style="text-align:left">
							<select id="isBindTelPhoneNumFlag" name="isBindTelPhoneNumFlag" class="easyui-combobox" 
								data-options=" 
								onSelect:function(data){
									$('#isBindTelPhoneNum').val(data.value);
								}" 
								style="width:50%; " editable=false>
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>是否大咖账户：</th>
						<td colspan="3" style="text-align:left">
							<select id="isDakaFlag" name="isDakaFlag" class="easyui-combobox" 
								data-options=" 
								onSelect:function(data){
									$('#isDaka').val(data.value);
								}" 
								style="width:50%; " editable=false>
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>IMEI码：</th>
						<td colspan="3" style="text-align:left">
							<input name="imei" value="${appUserVo.imei}"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>注册短信码：</th>
						<td colspan="3" style="text-align:left">
							<input name="msgCode" value="${appUserVo.msgCode}"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>APP来源渠道：</th>
						<td colspan="3" style="text-align:left">
							<input name="channel" value="${appUserVo.channel}"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>拉黑原因说明：</th>
						<td colspan="3" style="text-align:left">
							<input name="blackRuleDesc" value="${appUserVo.blackRuleDesc}"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
				</table>
				<input name="phoneNum" type="hidden" value="${appUserVo.phoneNum}"/>
				<input id="isBindWechat" name="isBindWechat" type="hidden" value="${appUserVo.isBindWechat}"/>
				<input id="isBindPhoneNum" name="isBindPhoneNum" type="hidden" value="${appUserVo.isBindPhoneNum}"/>
				<input id="isBindAlipay" name="isBindAlipay" type="hidden" value="${appUserVo.isBindAlipay}"/>
				<input id="isBindTelPhoneNum" name="isBindTelPhoneNum" type="hidden" value="${appUserVo.isBindTelPhoneNum}"/>
				<input id="isDaka" name="isDaka" type="hidden" value="${appUserVo.isDaka}"/>
				<input id="isMaster" name="isMaster" type="hidden" value="${appUserVo.isMaster}"/>
			</form>
		</div>
	</div> 
</div>
<script type="text/javascript">
$(function(){
	var select = document.getElementById("isBindWechatFlag");  
	var value = $('#isBindWechat').val();
	var isFlag = 0;
	for(var i=0; i<select.options.length; i++){  
	    if(select.options[i].value == value){
	        select.options[i].selected = true;
	        isFlag = 1;
	        break;
	    }
	}
	if(isFlag == 0){
		$('#isBindWechat').val(0);
	}
	
	var select = document.getElementById("isBindPhoneNumFlag");  
	var value = $('#isBindPhoneNum').val();
	var isFlag1 = 0;
	for(var i=0; i<select.options.length; i++){  
	    if(select.options[i].value == value){
	        select.options[i].selected = true;
	        isFlag1 = 1;
	        break;
	    }
	}
	if(isFlag1 == 0){
		$('#isBindPhoneNum').val(0);
	}
	
	var select = document.getElementById("isBindAlipayFlag");  
	var value = $('#isBindAlipay').val();
	var isFlag2 = 0;
	for(var i=0; i<select.options.length; i++){  
	    if(select.options[i].value == value){
	        select.options[i].selected = true;
	        isFlag2 = 1;
	        break;
	    }
	}
	if(isFlag2 == 0){
		$('#isBindTelPhoneNum').val(0);
	}
	
	var select = document.getElementById("isBindTelPhoneNumFlag");  
	var value = $('#isBindTelPhoneNum').val();
	var isFlag3 = 0;
	for(var i=0; i<select.options.length; i++){  
	    if(select.options[i].value == value){
	        select.options[i].selected = true;
	        isFlag3 = 1;
	        break;
	    }
	}
	if(isFlag3 == 0){
		$('#isBindTelPhoneNum').val(0);
	}
	
	var select = document.getElementById("isDakaFlag");  
	var value = $('#isDaka').val();
	var isFlag4 = 0;
	for(var i=0; i<select.options.length; i++){  
	    if(select.options[i].value == value){
	        select.options[i].selected = true;
	        isFlag4 = 1;
	        break;
	    }
	}
	if(isFlag4 == 0){
		$('#isDaka').val(0);
	}
	
	var select = document.getElementById("isMasterFlag");  
	var value = $('#isMaster').val();
	var isFlag5 = 0;
	for(var i=0; i<select.options.length; i++){  
	    if(select.options[i].value == value){
	        select.options[i].selected = true;
	        isFlag5 = 1;
	        break;
	    }
	}
	if(isFlag5 == 0){
		$('#isMaster').val(0);
	}
});
</script>