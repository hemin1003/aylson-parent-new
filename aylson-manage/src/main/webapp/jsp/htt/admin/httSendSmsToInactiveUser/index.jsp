<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/resources/inc/meta.jsp"></jsp:include>
<jsp:include page="/resources/inc/easyui.jsp"></jsp:include>
</head>
<body class="easyui-layout" fit="true">
	<div region="north" style="white-space: nowrap;padding: 5px; height: 50px;">
		<div style="margin-bottom: 5px">
			<form id="sendSmsToInactiveUserForm"  method="post">
				<table class="table_content"   border="0" >
					<tr>
					
					   <td class="tar" >手机号码：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="realPhoneNum" prompt="匹配查询"/>
						</td>
					
						<td class="tar" > 徒弟数大于</td>
						<td class="tal" >
							<input class="easyui-textbox" name="students" prompt="输入徒弟数"/>
						</td>
						
						<td class="tar" >未打开天数大于</td>
						<td class="tal" >
							<input class="easyui-textbox" name="unUseDay" prompt="输入天数"/>
						</td>
						
						<td class="tar" >金币余额大于</td>
						<td class="tal" >
							<input class="easyui-textbox" name="gold" prompt="输入金币数"/>
						</td>
						
					    <td style="padding-left:20px">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="doSearch()">搜索</a>
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" style="width:80px" onclick="reset()">重置</a>
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="sendSms()">发送短信</a>
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
<script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>/resources/js/aylson/htt/httSendSmsToInactiveUser.js?date=2016112516"></script>
</html>