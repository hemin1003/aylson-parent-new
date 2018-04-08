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
			<form id="inviteUserHisForm"  method="post">
				<table class="table_content"   border="0" >
					<tr>
						<td class="tar" >师傅全局身份唯一ID：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="masterPhoneNumLike" prompt="匹配查询"/>
						</td>
						<td class="tar" >师傅注册IP：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="masterRegisterIp" prompt="匹配查询"/>
						</td>
						<td class="tar" >邀请日期：</td>
						<td class="tal" >
							<input class="easyui-datebox" name="createDate" id="createDate"/>
						</td> 
						
						<td class="tar" >注册类型：</td>
						<td class="tal" >
							<select name="registerType" class="easyui-combobox"  style="width: 140px;" editable=false>
								<option value="">全部</option>
								<option value="1">直接邀请码注册</option>
								<option value="2">登录后再邀请码绑定</option>
								<option value="3">大咖用户IP注册</option>
							</select>
						</td>
						
					</tr>
					<tr>
						<td class="tar" >徒弟全局身份唯一ID：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="studentPhoneNumLike" prompt="匹配查询"/>
						</td> 
						<td class="tar" >徒弟注册IP：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="studentRegisterIp" prompt="匹配查询"/>
						</td>
						 <td style="padding-left:20px" colspan="2">
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
<script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>/resources/js/aylson/htt/inviteUserHis.js?date=2016112516"></script>
</html>