	/**
	 * 用户信息管理
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/htt/appUser/admin/list?v_date=' + new Date(),
			pagination : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			idField : 'phoneNum',
			singleSelect:true,
			rownumbers: true,
			toolbar:[{
				text:"刷新",
				iconCls : 'icon-reload',
				handler : reload
			}, {
				text:"黑名单导入",
				iconCls : 'icon-add',
				handler : importApply
			}],
 			frozenColumns : [[{ 
				field : 'opt',
				title : '操作选项',
				align : 'center',
				width : 100,
	 			formatter:function(value,row,index){
					var handleHtml = '';
					handleHtml += '<a href="javascript:query(\'' + row.phoneNum + '\')">查看</a>&nbsp;';
					handleHtml += '<a href="javascript:edit(\'' + row.phoneNum + '\')">修改</a>&nbsp;';
					if(row.blackList == 1){
						handleHtml += '<a href="javascript:changeStatus(\'' + row.phoneNum + '\',0)">取消黑名单</a>&nbsp;';
					}else{
						handleHtml += '<a href="javascript:changeStatus(\'' + row.phoneNum + '\',1)">设置黑名单</a>&nbsp;';
					}
					if(row.isBindWechat == 0){
						handleHtml += '<a href="javascript:alertNotBindWechat()">置为失效</a>&nbsp;';
					}else if(row.isBindWechat == 1){
						handleHtml += '<a href="javascript:invalidWechat(\'' + row.phoneNum + '\')">置为失效</a>&nbsp;';
					}
					if(row.isDaka == 1){
						handleHtml += '<a href="javascript:changeToBigUser(\'' + row.phoneNum + '\',0,\'' + row.inviteCode + '\',)"><font color=red>解绑大咖</font></a>&nbsp;';
					}else{
						handleHtml += '<a href="javascript:changeToBigUser(\'' + row.phoneNum + '\',1,\'' + row.inviteCode + '\',)">绑定大咖</a>&nbsp;';
					}
					return handleHtml;
				}
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
				width : 75,
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
				width : 55,
				sortable:true
			}, {
				title : '是否大咖',
				field : 'isDaka',
				align : 'center',
				width : 55,
				sortable:true,
				formatter:function(value,row,index){
					if(value == 1){
						return "是";
					}
					return "";
				}
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
	
	//新增
	function add(obj){
		var win;
		win = $("<div></div>").dialog({
			title:'新增',
			width:450,
			height:'60%',
			modal:true,
			href:projectName+'/htt/appUser/admin/toAdd',
			onClose:function(){
				$(this).dialog("destroy");
			},
			buttons:[{
				text:'确定',
			    iconCls:'icon-ok',
			    handler:function(){
				    	$("#appUserConfigForm").form('submit',{
				    		 type:'POST',
				    		 url : projectName+'/htt/appUser/admin/add',
				    		 success:function(responseData){
				    			 if(responseData){
				    				var data = $.parseJSON(responseData);
				    			 	$.messager.show({"title":"系统提示","msg":data.message,"timeout":1000});
				    			 	if(data.success){
										$("#datagrid").datagrid("reload");
										win.dialog('destroy');
				    				}
				    			 } 
				    		 }
				    	 });
			    }   
			   },{
				 text:'取消',
			     iconCls:'icon-cancel',  
			 	 handler:function(){
			 		 win.dialog('destroy');
			 	 }   
			  }]
		});
	}
	
	//修改
	function edit(phoneNum){
		win = $("<div></div>").dialog({
			title:'修改',
			width:450,
			height:'60%',
			maximizable:true,
			modal:true,
			href:projectName+'/htt/appUser/admin/toEdit?phoneNum='+phoneNum,
			onClose:function(){
		    		$(this).dialog("destroy");
		    },
			buttons:[{
					text:'确定',
				    iconCls:'icon-ok',
				    handler:function(){
					    	$("#appUserConfigForm").form('submit',{
					    		 type:'POST',
					    		 url : projectName+'/htt/appUser/admin/update',
					    		 success:function(responseData){
					    			 win.dialog('destroy');
					    			 if(responseData){
					    				var data = $.parseJSON(responseData);
					    			 	$.messager.show({"title":"系统提示","msg":data.message,"timeout":1000});
					    			 	if(data.success){
					    			 		$("#datagrid").datagrid("reload");
					    				}
					    			 } 
					    		 }
					    	 });
				     }   
				   },{
					 text:'取消',
				     iconCls:'icon-cancel',  
				 	 handler:function(){
				 		 win.dialog('destroy');
				 	 }   
				  }]
		});
	}
	
	//查看
	function query(phoneNum){
		win = $("<div></div>").dialog({
			title:'查看',
			width:550,
			height:'80%',
			maximizable:true,
			modal:true,
			href:projectName+'/htt/appUser/admin/toShowMore?phoneNum='+phoneNum,
			onClose:function(){
		    		$(this).dialog("destroy");
		    },
			buttons:[{
					 text:'取消',
				     iconCls:'icon-cancel',  
				 	 handler:function(){
				 		 win.dialog('destroy');
				 	 }   
				  }]
		});
	}

	//删除
	function del(id){
		$.messager.confirm("提示","确定删除此记录吗？",function(r){
			if(r){
				$.ajax({
					type:"POST",
					url:projectName+'/htt/appUser/admin/deleteById?id=' + id,
					dataType:"json",
					success:function(data){
						if(data){
		    				$.messager.show({"title":"系统提示","msg":data.message,"timeout":1000});
		    				if(data.success){
		    					$("#datagrid").datagrid("reload");
		    				}
		    			 }
					}
				});
			}
		});
	}
	
	//发布
	function changeStatus(phoneNum, status){
		var tip = "";
		if(status == 1){
			tip = "确定设置黑名单吗？";
			
		}else if(status == 0){
			tip = "确定取消黑名单吗？";
		}
		$.messager.confirm("提示",tip,function(r){
			if(r){
				$.ajax({
					type:"POST",
					url:projectName+'/htt/appUser/admin/changeStatus?phoneNum=' + phoneNum+'&blackList='+status,
					dataType:"json",
					success:function(data){
						if(data){
		    					$.messager.show({"title":"系统提示","msg":data.message,"timeout":1000});
			    				if(data.success){
			    					$("#datagrid").datagrid("reload");
			    				}
		    			 	}
					}
				});
			}
		});
	}
	
	//大咖管理
	function changeToBigUser(phoneNum, status, inviteCode){
		var tip = "";
		if(status == 1){
			tip = "确定设置成大咖身份吗？";
			
		}else if(status == 0){
			tip = "确定取消大咖身份吗？";
		}
		$.messager.confirm("提示",tip,function(r){
			if(r){
				$.ajax({
					type:"POST",
					url:projectName+'/htt/appUser/admin/changeToBigUser?phoneNum=' + phoneNum+'&isDaka='+status+'&inviteCode='+inviteCode,
					dataType:"json",
					success:function(data){
						if(data){
		    					$.messager.show({"title":"系统提示","msg":data.message,"timeout":1000});
			    				if(data.success){
			    					$("#datagrid").datagrid("reload");
			    				}
		    			 	}
					}
				});
			}
		});
	}
	
	//刷新
	function reload(){
		$("#datagrid").datagrid("reload");
	}
	
	//搜索
	function doSearch(){
		$("#datagrid").datagrid("load", serializeObject($("#appUserForm")));
	}
	
	//重置
	function reset(){
		$("#appUserForm").form("reset");
	}
	
	function importApply(){
		win = $("<div></div>").dialog({
			title:'导入Excel',
			width:650,
			height:'30%',
			maximizable:true,
			modal:true,
			href:projectName+'/htt/appUser/admin/toImport',
			onClose:function(){
		    		$(this).dialog("destroy");
		    }
		});
	}
	
	//将微信置为失效
	function invalidWechat(phoneNum){
		$.messager.confirm("提示","确定要将微信置为失效吗？",function(r){
			if(r){
				$.ajax({
					type:"POST",
					url:projectName+'/htt/appUser/admin/invalidWechat?phoneNum=' + phoneNum,
					dataType:"json",
					success:function(data){
						if(data){
		    				$.messager.show({"title":"系统提示","msg":data.message,"timeout":1000});
		    				if(data.success){
		    					$("#datagrid").datagrid("reload");
		    				}
		    			 }
					}
				});
			}
		});
	}
	
	//提示未绑定微信
	function alertNotBindWechat(){
		$.messager.show({"title":"系统提示","msg":"该用户没有绑定微信","timeout":1000});
	}