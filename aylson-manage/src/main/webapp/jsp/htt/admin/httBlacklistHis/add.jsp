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

<script type="text/javascript">
function preview(){  
    var simg = $('#titleMapUrl').val();;  
    wins = $("<div align='center' style='text-align:center; background:#90A4AE'><img id='simg'/></div>").dialog({
		title:'大图预览',
		width:'95%',
		height:'95%',
		maximizable:true,
		modal:true,
		onClose:function(){
	    		$(this).dialog("destroy");
	    },
	});
    $("#simg").attr("src",simg);  
}
</script>

<div align="center" >
	<div class="easyui-tabs" id="tabActivity" style="width:100%">
		 <div title="公告信息" style="padding:10px;text-align:center">
		 	<form id="httNoticeConfigForm" method="post">
				<table class="tableForm" style="width:99%;">
					
					<tr>
						<th>标题：</th>
						<td colspan="3" style="text-align:left">
							<input name="title" value="${noticeConfigVo.title}" data-options="required:true"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>描述：</th>
						<td colspan="3" style="text-align:left">
							<input name="description" value="${noticeConfigVo.description}" data-options="required:true"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>按钮文案：</th>
						<td colspan="3" style="text-align:left">
							<input name="buttonCopywriting" value="${noticeConfigVo.buttonCopywriting}" data-options="required:true"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>消息类型：</th>
						<td colspan="3" style="text-align:left">
							<select id="notificationTypeFlag" name="notificationTypeFlag" class="easyui-combobox" " 
								data-options=" 
								onSelect:function(data){
									$('#notificationType').val(data.value);
								}" 
								style="width:95%; " editable=false>
								<option value="1">消息中心</option>
								<option value="2">首页置顶</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>动态参数字段：</th>
						<td colspan="3" style="text-align:left">
							<div id="editor" style="width:90%;height:450px;">${noticeConfigVo.action}
							</div>
						</td>
					</tr>
					
					<tr>
						<th>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'" 
								onclick="uploadImg('img','titleMapUrl','yfax-test')" id="uploadImg" style="margin-bottom:10px;">上传标题图</a>
						</th>
						<td colspan="3" style="text-align:left">
							<div style="width:240px;height:120px">
								<c:if test="${not empty noticeConfigVo.titleMapUrl}"><img id="img" src="${noticeConfigVo.titleMapUrl}" style="width:120px;height:120px"/></c:if>
								<c:if test="${empty noticeConfigVo.titleMapUrl }"><img id="img" src="" style="width:120px;height:120px"/></c:if>
								<input id="titleMapUrl" name="titleMapUrl" value="${noticeConfigVo.titleMapUrl}" type="hidden"/>
							</div>
							<c:if test="${not empty noticeConfigVo.titleMapUrl}"><a href="javascript:preview()">查看大图</a></c:if>
						</td>
					</tr>
					
				</table>
				<input name="id" type="hidden" value="${noticeConfigVo.id}"/>
				<input name="action" id="action" type="hidden" value=""/>
				<input name="notificationType" id="notificationType" value="${noticeConfigVo.notificationType}" type="hidden"/>
			</form>
		</div>
	</div> 
</div>

<script>
var editor = ace.edit("editor");
editor.setTheme("ace/theme/monokai");
editor.getSession().setMode("ace/mode/javascript");

$(function(){
	var select = document.getElementById("notificationTypeFlag");  
	var value = $('#notificationType').val();
	var isFlag = 0;
	for(var i=0; i<select.options.length; i++){  
	    if(select.options[i].value == value){
	        select.options[i].selected = true;
	        isFlag = 1;
	        break;
	    }
	}
	if(isFlag == 0){
		$('#notificationType').val(1);
	}
});
</script>
