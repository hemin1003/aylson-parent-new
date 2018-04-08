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
		 <div id="importExcel" title="导入操作" style="padding:10px;text-align:center">
		 	<form id="uploadExcel" method="post" enctype="multipart/form-data">
		 		选择文件：　<input id="excel" name="excel" class="easyui-filebox" style="width:500px" data-options="prompt:'请选择文件...'">
			</form>
			<div style="text-align: center; padding: 5px 0;">
                <a id = "booten" href="javascript:void(0)" class="easyui-linkbutton"
                    onclick="uploadExcel()" style="width: 80px" id="tt">导入</a>
            </div>
		</div>
	</div> 
</div>

<script type="text/javascript">
function uploadExcel() {
	
    $("#uploadExcel").form({
        type : 'post',
        url : projectName+'/htt/appUser/admin/importApply',
        dataType : "json",
        onSubmit: function() {
            var fileName= $('#excel').filebox('getValue'); 
              //对文件格式进行校验  
             var d1=/\.[^\.]+$/.exec(fileName);
            if (fileName == "") {  
                  $.messager.alert('Excel导入', '请选择将要上传的文件!'); 
                  return false;  
             }else if(d1!=".xls"){
                 $.messager.alert('提示','请选择xls格式文件！','info');  
                 return false; 
             }
             $("#booten").linkbutton('disable');
            return true;  
        }, 
        success : function(result) {
            var result = eval('(' + result + ')');
            if (result.success == 0) {
                $.messager.alert('提示!', '导入成功','info',
                        function() {
                            $("#datagrid").datagrid("reload");
                            win.dialog('destroy');
                        });
            } else {
                $.messager.confirm('提示',"导入失败!");
                $("#booten").linkbutton('enable');
            }
        }
    });

	$("#uploadExcel").form('submit');
}
</script>