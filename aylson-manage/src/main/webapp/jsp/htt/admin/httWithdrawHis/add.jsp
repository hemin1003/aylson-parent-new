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
		 	<form id="httWithdrawHisConfigForm" method="post">
				<table class="tableForm" style="width:99%;">
					<tr>
						<th>手机号码：</th>
						<td colspan="3" style="text-align:left">
							<input name="phoneNum" value="${httWithdrawHisVo.phoneNum}"
								class="easyui-textbox"
								style="width:95%; text-align:left" readOnly=true/>
						</td>
					</tr>
					<tr>
						<th>提现类型：</th>
						<td colspan="3" style="text-align:left">
							<input name="withdrawName" value="${httWithdrawHisVo.withdrawName}"
								class="easyui-textbox"
								style="width:95%; text-align:left" readOnly=true/>
						</td>
					</tr>
					<tr>
						<th>姓名：</th>
						<td colspan="3" style="text-align:left">
							<input name="name" value="${httWithdrawHisVo.name}"
								class="easyui-textbox"
								style="width:95%; text-align:left" readOnly=true/>
						</td>
					</tr>
					<tr>
						<th>账户名：</th>
						<td colspan="3" style="text-align:left">
							<input name="account" value="${httWithdrawHisVo.account}"
								class="easyui-textbox"
								style="width:95%; text-align:left" readOnly=true/>
						</td>
					</tr>
					<tr>
						<th>兑换金币：</th>
						<td colspan="3" style="text-align:left">
							<input name="gold" value="${httWithdrawHisVo.gold}"
								class="easyui-textbox"
								style="width:95%; text-align:left" readOnly=true/>
						</td>
					</tr>
					<tr>
						<th>提现金额：</th>
						<td colspan="3" style="text-align:left">
							<input name="income" value="${httWithdrawHisVo.income}"
								class="easyui-textbox"
								style="width:95%; text-align:left" readOnly=true/>
						</td>
					</tr>
					<tr>
						<th>提现发起时间：</th>
						<td colspan="3" style="text-align:left">
							<input name="withdrawTime" value="${httWithdrawHisVo.withdrawTime}"
								class="easyui-datetimebox"
								style="width:95%; text-align:left" readOnly=true/>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>提现状态：</th>
						<td colspan="3" style="text-align:left">
							<select id="statusTypeFlag" name="statusTypeFlag" class="easyui-combobox" 
								data-options=" 
								onSelect:function(data){
									$('#status').val(data.text);
									$('#statusType').val(data.value);
								}" 
								style="width:50%; " editable=false>
								<option value="1">处理中</option>
								<option value="2">提现成功</option>
								<!-- <option value="2">支付成功</option> -->
								<!-- <option value="3">充值成功</option> -->
								<option value="4">提现失败</option>
								<option value="5">审核中</option>
								<option value="6">审核成功-财务打款中，请耐心等候</option>
								<option value="7">审核失败</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan=4>
							<br/>
							<font color=red size=2>特殊说明：当‘提现失败’时，系统会自动回款，不能再审核!</font>
						</td>
					</tr>
				</table>
				<input name="id" type="hidden" value="${httWithdrawHisVo.id}"/>
				<input id="statusType" name="statusType" type="hidden" value="${httWithdrawHisVo.statusType}"/>
				<input name="status" id="status" value="${httWithdrawHisVo.status}" type="hidden"/>
				<input name="withdrawType" value="${httWithdrawHisVo.withdrawType}" type="hidden"/>
				<input name="statusTypeOld" id="statusTypeOld" value="${httWithdrawHisVo.statusType}" type="hidden"/>
				
			</form>
		</div>
	</div> 
</div>
<script type="text/javascript">
$(function(){
	var select = document.getElementById("statusTypeFlag");  
	var value = $('#statusTypeOld').val();
	var isFlag = 0;
	for(var i=0; i<select.options.length; i++){  
	    if(select.options[i].value == value){
	        select.options[i].selected = true;
	        isFlag = 1;
	        break;
	    }
	}
	if(isFlag == 0){
		$('#statusTypeFlag').val(1);
	}
});
</script>