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
			<form id="awardHisForm"  method="post">
				<table class="table_content"   border="0" >
					<tr>
					
						<td class="tar" >全局身份唯一ID：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="phoneNumLike" prompt="匹配查询"/>
						</td> 
						
						<td class="tar" >奖励类型：</td>
						<td class="tal" >
							<select name="awardType" class="easyui-combobox"  style="width: 140px;" editable=false>
								<option value="">全部</option>
								<option value="1">首次有效阅读奖励</option>
								<option value="2">邀请奖励</option>
								<option value="3">每日签到</option>
								<option value="4">阅读奖励</option>
								<option value="5">徒弟贡献奖励</option>
								<option value="6">首次分享奖励</option>
								<option value="7">首次邀请奖励</option>
								<option value="8">每日自动扣减</option>
								<option value="9">注册奖励</option>
								<option value="10">时段奖励</option>
								<option value="11">阅读累积时长奖励</option>
								<option value="12">视频累积时长奖励</option>
								<option value="13">观看视频奖励</option>
								<option value="14">完成任务奖励</option>
								<option value="15">金币兑换/返还</option>
								<option value="16">系统补偿</option>
								<option value="17">游戏任务奖励</option>
								<option value="18">抽奖奖励</option>
							</select>
						</td>
						
						<td class="tar" >记录时间：</td>
						<td class="tal" >
							<input class="easyui-datebox" name="createDate" id="createDate"/>
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
<script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>/resources/js/aylson/htt/awardHis.js?date=2016112516"></script>
</html>