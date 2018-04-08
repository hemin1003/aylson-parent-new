<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
</head>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.6/summernote.css" rel="stylesheet">

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
		 <div title="全局信息配置" style="padding:10px;text-align:center">
		 	<form id="appConfigConfigForm" method="post">
				<table class="tableForm" style="width:99%;">
					<tr>
						<th>每日金币领取上限：</th>
						<td colspan="3" style="text-align:left">
							<input name="goldLimit" value="${appConfigVo.goldLimit}" data-options="required:true"
								class="easyui-numberbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>阅读金币随机范围：</th>
						<td colspan="3" style="text-align:left">
							<input name="goldRange" value="${appConfigVo.goldRange}" data-options="required:true"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>时段奖励间隔时长：</th>
						<td colspan="3" style="text-align:left">
							<input name="timeDuration" value="${appConfigVo.timeDuration}" data-options="required:true"
								class="easyui-numberbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>时段奖励金币值：</th>
						<td colspan="3" style="text-align:left">
							<input name="timeGold" value="${appConfigVo.timeGold}" data-options="required:true"
								class="easyui-numberbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>每日视频金币领取上限：</th>
						<td colspan="3" style="text-align:left">
							<input name="videoGoldLimit" value="${appConfigVo.videoGoldLimit}" data-options="required:true"
								class="easyui-numberbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>视频金币随机范围和概率值：</th>
						<td colspan="3" style="text-align:left">
							<input name="videoGoldRange" value="${appConfigVo.videoGoldRange}" data-options="required:true"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>动态参数字段：</th>
						<td colspan="3" style="text-align:left">
							<div id="editor" style="width:90%;height:450px;">
								${appConfigVo.params}
							</div>
						</td>
					</tr>
					<tr>
						<th>阅读收益说明：</th>
						<td colspan="3" style="text-align:left">
							<div id="summernote" style="width:90%">
								${appConfigVo.readRuleDesc}
							</div>
						</td>
					</tr>
					
					<tr>
						<th>安卓分享下载链接Url：</th>
						<td colspan="3" style="text-align:left">
							<input name="androidUrl" value="${appConfigVo.androidUrl}" data-options="required:true"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>苹果分享下载链接Url：</th>
						<td colspan="3" style="text-align:left">
							<input name="iphoneUrl" value="${appConfigVo.iphoneUrl}" data-options="required:true"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>阅读奖励次数限制(提示验证码)：</th>
						<td colspan="3" style="text-align:left">
							<input name="readAwardLimit" value="${appConfigVo.readAwardLimit}" data-options="required:true"
								class="easyui-numberbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>天翼悦头条包下载地址Url：</th>
						<td colspan="3" style="text-align:left">
							<input name="tyDownloadUrl" value="${appConfigVo.tyDownloadUrl}" data-options="required:true"
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>单片文章可获得奖励次数：</th>
						<td colspan="3" style="text-align:left">
							<input name="readAwardRepeat" value="${appConfigVo.readAwardRepeat}" data-options="required:true"
								class="easyui-numberbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
					<tr>
						<th>单个视频可获得奖励次数：</th>
						<td colspan="3" style="text-align:left">
							<input name="videoAwardRepeat" value="${appConfigVo.videoAwardRepeat}" data-options="required:true"
								class="easyui-numberbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					
				</table>
				<input name="id" type="hidden" value="${appConfigVo.id}"/>
				<input name="params" id="params" type="hidden" value=""/>
				<input name="readRuleDesc" id="readRuleDesc" type="hidden" value=""/>
			</form>
		</div>
	</div> 
</div>
<script>
var editor = ace.edit("editor");
editor.setTheme("ace/theme/monokai");
editor.getSession().setMode("ace/mode/javascript");

function createEditor(){
	$('#summernote').summernote({
		height: 160,
		focus: true,
		lang: 'zh-CN'   	    		  
	});
}
$("#tabActivity").tabs({
	onSelect:function(title,index){
		if(index == 0){
			createEditor();
		}
	}
})
</script>