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
         <div title="修改账户" style="padding:10px;text-align:center">
            <form id="bigUserConfigForm" method="post">
                <table class="tableForm" style="width:99%;">
                    <tr>
                        <th>手机号码<font color='red'>（只读）</font>：</th>
                        <td colspan="3" style="text-align:left">
                            <input value="${bigUserVo.phoneNum}" data-options="required:true" readOnly=true
                                class="easyui-textbox"
                                style="width:95%; text-align:left"/>
                        </td>
                    </tr>
                    <tr>
                        <th>邀请码<font color='red'>（只读）</font>：</th>
                        <td colspan="3" style="text-align:left">
                            <input value="${bigUserVo.inviteCode}" data-options="required:true" readOnly=true
                                class="easyui-textbox"
                                style="width:95%; text-align:left"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <th>专属邀请链接：</th>
                        <td colspan="3" style="text-align:left">
                            <input name="inviteUrl" value="${bigUserVo.inviteUrl}" data-options="required:false"
                                class="easyui-textbox"
                                style="width:95%; text-align:left"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <th>下载链接：</th>
                        <td colspan="3" style="text-align: left">
                            <input name="downloadUrl" value="${bigUserVo.downloadUrl}"
                            class="easyui-textbox"
                            style="width:95%; text-align:left"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <th>后台绑定登录账户：</th>
                        <td colspan="3" style="text-align:left">
                            <input name="sysUserId" value="${bigUserVo.sysUserId}"
                                class="easyui-textbox"
                                style="width:95%; text-align:left"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <th>用户描述信息：</th>
                        <td colspan="3" style="text-align:left">
                            <input name="userDesc" value="${bigUserVo.userDesc}"
                                class="easyui-textbox"
                                style="width:95%; text-align:left"/>
                        </td>
                    </tr>
                </table>
                <input name="phoneNum" type="hidden" value="${bigUserVo.phoneNum}"/>
            </form>
        </div>
    </div> 
</div>