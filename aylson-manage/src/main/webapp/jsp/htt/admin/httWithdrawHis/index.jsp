<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/resources/inc/meta.jsp"></jsp:include>
<jsp:include page="/resources/inc/easyui.jsp"></jsp:include>
</head>
<body class="easyui-layout" fit="true">
	<div region="north" style="white-space: nowrap;padding: 5px; height: 70px;">
		<div style="margin-bottom: 5px">
			<form id="httWithdrawHisForm"  method="post">
				<table class="table_content"   border="0" >
					<tr>
					
					    <td class="tar" >订单流水号：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="id" prompt="匹配查询"/>
						</td>
						
						<td class="tar" >提现类型：</td>
						<td class="tal" >
							<select name="withdrawType" class="easyui-combobox"  style="width: 120px;" editable=false>
								<option value="">全部</option>
								<option value="1">手机充值</option>
								<option value="2">支付宝</option>
								<option value="3">微信</option>
								<option value="4">QQ币</option>
							</select>
						</td>
						<td class="tar" >提现状态：</td>
						<td class="tal" >
							<select name="statusType" id="statusType" class="easyui-combobox"  style="width: 120px;" editable=false>
								<option value="">全部</option>
								<option value="1">处理中</option>
								<!-- <option value="2">支付成功</option> -->
								<option value="2">提现成功</option>
								<option value="3">充值成功</option>
								<option value="4">提现失败</option>
								<option value="5">审核中</option>
								<option value="6">审核成功</option>
								<option value="7">审核失败</option>
							</select>
						</td>
						<td class="tar" >姓名：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="nameLike" prompt="模糊查询"/>
						</td>
						<td class="tar" >提现完成日期：</td>
						<td class="tal" >
							<input class="easyui-datebox" name="updateDate" id="updateDate"/>
						</td> 
					</tr>
					<tr>
						<td class="tar" >账户名：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="accountLike" prompt="模糊查询"/>
						</td>
						<td class="tar" >全局身份唯一ID：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="phoneNumLike" prompt="模糊查询"/>
						</td>
						<td class="tar" >提现日期：</td>
						<td class="tal" >
							<input class="easyui-datebox" name="withdrawTime" id="withdrawTime"/>
						</td> 
					    <td style="padding-left:20px" colspan=2>
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="doSearch()">搜索</a>
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" style="width:80px" onclick="reset()">重置</a>
						</td> 
					</tr>
				</table>
			</form>
		</div>
	</div> 
	<div data-options="region:'center'" border="false" style="overflow: hidden;width:85%">
    	<table id="datagrid"></table>
    </div>
</body>
<script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>/resources/js/aylson/htt/httWithdrawHis.js?date=2016112516"></script>
</html>