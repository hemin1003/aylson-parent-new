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
		 <div title="提现操作" style="padding:10px;text-align:center">
		 	<form id="bigUserWithdrawConfigForm" method="post">
				<table class="tableForm" style="width:99%;">
					<tr>
						<th>手机号码：</th>
						<td colspan="3" style="text-align:left">
							<input name="phoneNum" value="${httWithdrawHisVo.phoneNum}"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>姓名：</th>
						<td colspan="3" style="text-align:left">
							<input name="name" value="${httWithdrawHisVo.name}"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>账户名：</th>
						<td colspan="3" style="text-align:left">
							<input name="account" value="${httWithdrawHisVo.account}"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>兑换金币：</th>
						<td colspan="3" style="text-align:left">
							<input name="gold" value="${httWithdrawHisVo.gold}"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<!-- 
					<tr>
						<th>提现金额：</th>
						<td colspan="3" style="text-align:left">
							<input name="income" value="${httWithdrawHisVo.income}"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					 -->
					<tr>
						<th><font color="red">*</font>提现类型：</th>
						<td colspan="3" style="text-align:left">
							<select id="withdrawTypeType" name="withdrawTypeType" class="easyui-combobox" 
								data-options=" 
								onSelect:function(data){
									$('#withdrawType').val(data.value);
								}" 
								style="width:50%; " editable=false>
								<option value="1">手机话费</option>
								<option value="2">支付宝</option>
								<option value="3">微信</option>
							</select>
						</td>
					</tr>
				</table>
				<input name="id" type="hidden" value="${httWithdrawHisVo.id}"/>
				<input name="withdrawType" id="withdrawType" value="${httWithdrawHisVo.withdrawType}" type="hidden"/>
			</form>
		</div>
	</div> 
</div>
<script type="text/javascript">
$(function(){
	var select = document.getElementById("withdrawTypeType");  
	var value = $('#withdrawType').val();
	var flag = 0;
	for(var i=0; i<select.options.length; i++){  
	    if(select.options[i].value == value){
	        select.options[i].selected = true;
	        flag = 1;
	        break;  
	    }
	}  
	if(flag == 0){
		$('#withdrawType').val(1);
	}
});
</script>