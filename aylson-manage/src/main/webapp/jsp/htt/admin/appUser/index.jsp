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
			<form id="appUserForm"  method="post">
				<table class="table_content"   border="0" >
					<tr>
					
						<td class="tar" >全局身份唯一ID：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="phoneNumLike" prompt="匹配查询"/>
						</td>
						
						<td class="tar" >手机号码：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="realPhoneNum" prompt="匹配查询"/>
						</td>
						
						<td class="tar" >邀请码：</td>
						<td class="tal">
						    <input class="easyui-textbox" name="inviteCode" prompt="匹配查询"/>
						</td>
						
						<td class="tar" >姓名：</td>
						<td class="tal">
						    <input class="easyui-textbox" name="userNameLike" prompt="模糊查询"/>
						</td>
						
						<td class="tar" >是否大咖：</td>
						<td class="tal" >
							<select name="isDaka" class="easyui-combobox"  style="width: 120px;" editable=false>
								<option value="">全部</option>
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>
						<td class="tar" >按徒弟数排列：</td>
						<td class="tal" >
							<select name="isOrderByStudents" class="easyui-combobox"  style="width: 120px;" editable=false>
								<option value="">全部</option>
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>
					    <td style="padding-left:20px">
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
<script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>/resources/js/aylson/htt/appUser.js?date=2016112516"></script>
</html>