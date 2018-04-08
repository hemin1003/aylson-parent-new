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
			<form id="httBlacklistHisForm"  method="post">
				<table class="table_content"   border="0" >
					<tr>
					
					   <td class="tar" >全局身份唯一ID：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="phoneNum" prompt="匹配查询"/>
						</td>
						
						<td class="tar" >命中来源：</td>
						<td class="tal" >
							<select name="source" class="easyui-combobox"  style="width: 120px;" editable=false>
								<option value="">全部</option>
								<option value="1">线上</option>
								<option value="2">线下</option>
							</select>
						</td>
						
						<td class="tar" >账户标记状态：</td>
						<td class="tal" >
							<select name="accountStatus" class="easyui-combobox"  style="width: 120px;" editable=false>
								<option value="">全部</option>
								<option value="0">正常</option>
								<option value="1">疑似</option>
								<option value="2">封禁</option>
							</select>
						</td>
					
					    <td class="tar" >日期：</td>
						<td class="tal" >
							<input class="easyui-datebox" name="createDate" id="createDate"/>
						</td> 
						
					    <td style="padding-left:20px">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="doSearch()">查询</a>
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
<script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>/resources/js/aylson/htt/httBlacklistHis.js?date=2016112516"></script>
</html>