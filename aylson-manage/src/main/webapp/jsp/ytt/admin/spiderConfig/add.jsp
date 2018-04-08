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
		 <div title="爬虫短信配置" style="padding:10px;text-align:center">
		 	<form id="spiderConfigForm" method="post">
				<table class="tableForm" style="width:99%;">
					<tr>
						<th>域名标识：</th>
						<td colspan="3" style="text-align:left">
							<input name="domain" value="${spiderConfigVo.domain}" data-options="required:true" readOnly=true
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>域名名称：</th>
						<td colspan="3" style="text-align:left">
							<input name="domainName" value="${spiderConfigVo.domainName}" data-options="required:true" readOnly=true
								class="easyui-textbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>报警阀值：</th>
						<td colspan="3" style="text-align:left">
							<input name="alpha" value="${spiderConfigVo.alpha}" data-options="required:true"
								class="easyui-numberbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>并发请求数：</th>
						<td colspan="3" style="text-align:left">
							<input name="reqCount" value="${spiderConfigVo.reqCount}" data-options="required:true"
								class="easyui-numberbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
					<tr>
						<th>并发处理线程数：</th>
						<td colspan="3" style="text-align:left">
							<input name="threadCount" value="${spiderConfigVo.threadCount}" data-options="required:true"
								class="easyui-numberbox"
								style="width:95%; text-align:left"/>
						</td>
					</tr>
				</table>
				<input name="id" type="hidden" value="${spiderConfigVo.id}"/>
			</form>
		</div>
	</div> 
</div>