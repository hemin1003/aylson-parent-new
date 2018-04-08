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
			<form id="traceHisForm"  method="post">
				<table class="table_content"   border="0" >
					<tr>
						<td class="tar" >全局身份唯一ID：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="phoneNum" prompt="匹配查询"/>
						</td>
						<td class="tar" >跟踪ID：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="traceId" prompt="匹配查询"/>
						</td>
						<td class="tar" >当前请求新闻渠道：</td>
						<td class="tal" >
							<select name="source" class="easyui-combobox"  style="width: 140px;" editable=false>
								<option value="">全部</option>
								<option value="1">百度新闻</option>
								<option value="2">东方头条</option>
								<option value="3">今日头条</option>
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
<script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>/resources/js/aylson/htt/traceHis.js?date=2016112516"></script>
</html>