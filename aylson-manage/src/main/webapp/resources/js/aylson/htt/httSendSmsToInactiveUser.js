	/**
	 * 短信拉活
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/htt/httSendSmsToInactiveUser/admin/list?v_date=' + new Date(),
			pagination : true,
			pageSize : 20,
			pageList : [ 20, 50, 100, 200, 300, 400, 500,1000],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			idField : 'phoneNum',
			singleSelect:false,
			rownumbers: true,
			toolbar:[{
				text:"刷新",
				iconCls : 'icon-reload',
				handler : reload
			}],
 			frozenColumns : [[{
				field : 'ck',
				checkbox:true,
				align:'center',
				width : 50
			}, {
				title : '全局身份唯一ID',
				field : 'phoneNum',
				align : 'center',
				width : 95,
				sortable:true
			}, {
				title : '手机号码',
				field : 'realPhoneNum',
				align : 'center',
				width : 85,
				sortable:true,
				formatter:function(value,row,index){
					if(row.phoneNum.length == 11){
						return row.phoneNum;
					}
					return value;
				}
			}, {
				title : '账户状态',
				field : 'accountStatus',
				align : 'center',
				width : 60,
				sortable:true,
				formatter:function(value,row,index){
					if(value == 0){
						return "<font color=green>正常</font>";
					}else if(value == 1){
						return "<font color=blue>疑似</font>";
					}else if(value == 2){
						return "<font color=red>封禁</font>";
					}
					return "正常";
				}
			}, {
				title : '姓名',
				field : 'userName',
				align : 'center',
				width : 60,
				sortable:true
			}, {
				title : '金币余额',
				field : 'gold',
				align : 'center',
				width : 60,
				sortable:true
			}, {
				title : '是否黑名单',
				field : 'blackList',
				align : 'center',
				width : 80,
				sortable:true,
				formatter:function(value,row,index){
					if(value == 1){
						return "<font color=red>是</font>";
					}
					return "";
				}
			}, {
				title : '徒弟数',
				field : 'students',
				align : 'center',
				width : 60,
				sortable:true,
				formatter:function(value,row,index){
					if(value){
						return value;
					}
					return "";
				}
			}, {
				title : '今日签到标识',
				field : 'continueCheckIn',
				align : 'center',
				width : 80,
				sortable:true,
				formatter:function(value,row,index){
					if(value == 1){
						return value;
					}
					return "";
				}
			}, {
				title : '邀请码',
				field : 'inviteCode',
				align : 'center',
				width : 60,
				sortable:true
			}, {
				title : '注册时间',
				field : 'registerDate',
				align : 'center',
				width : 120,
				sortable:true,
				formatter:function(value,row,index){
					if(value){
						return value.substring(0,19);
					}
					return value;
				}
			}, {
				title : '注册IP',
				field : 'registerIp',
				align : 'center',
				width : 90,
				sortable:true
			}, {
				title : '最近一次活动时间',
				field : 'updateDate',
				align : 'center',
				width : 120,
				sortable:true,
				formatter:function(value,row,index){
					if(value){
						return value.substring(0,19);
					}
					return value;
				}
			}, {
				title : '最后一次登录时间',
				field : 'lastLoginDate',
				align : 'center',
				width : 120,
				sortable:true,
				formatter:function(value,row,index){
					if(value){
						return value.substring(0,19);
					}
					return value;
				}
			}
			] ]
		});
		
	});
	
	//刷新
	function reload(){
		$("#datagrid").datagrid("reload");
	}
	
	//搜索
	function doSearch(){
		$("#datagrid").datagrid("load", serializeObject($("#sendSmsToInactiveUserForm")));
	}
	
	//重置
	function reset(){
		$("#sendSmsToInactiveUserForm").form("reset");
		$('#datagrid').datagrid('unselectAll');
	}
	
	//发送短信
	function sendSms() {
		var rows =  $('#datagrid').datagrid('getChecked');
		var realPhoneNums = [];
		var tip;
		if (rows.length != 0){
			for(var i=0; i<rows.length; i++){
				realPhoneNums.push(rows[i].realPhoneNum);
			}
			tip="已选择" + rows.length +"个用户，确定发送短信吗？"
		}else{
			tip = "确定全部发送短信吗？";
		}
		
		$.messager.confirm("提示",tip,function(r){
			if(r){
				msgLoad();
				$.ajax({
					type:"POST",
					url:projectName+'/htt/httSendSmsToInactiveUser/admin/sendSms',
//					data:realPhoneNums.join(","),
					data:{'realPhoneNums':realPhoneNums},
					dataType:"json",
					success:function(data){
						if(data){
		    					$.messager.show({"title":"系统提示","msg":data.message,"timeout":1000});
			    				if(data.success){
			    					$("#datagrid").datagrid("reload");
			    					$('#datagrid').datagrid('unselectAll');
			    					msgDisLoad();
			    				}
		    			 	}
					}
				});
			}else{
				$('#datagrid').datagrid('unselectAll');
			}
			
		});
	}
	
	//弹出加载层
	function msgLoad() {  
	    $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
	    $("<div class=\"datagrid-mask-msg\"></div>").html("短信发送中，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });  
	}
	//取消加载层  
	function msgDisLoad() {  
	    $(".datagrid-mask").remove();  
	    $(".datagrid-mask-msg").remove();  
	}
	