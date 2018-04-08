	/**
	 * 大咖用户注册管理
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/htt/bigUser/admin/list?v_date=' + new Date(),
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
				text:"新增",
				iconCls : 'icon-add',
				handler : add
			},{
				text:"刷新",
				iconCls : 'icon-reload',
				handler : reload
			}],
 			frozenColumns : [[{ 
				field : 'opt',
				title : '操作选项',
				align : 'center',
				width : 100,
	 			formatter:function(value,row,index){
					var handleHtml = '';
					handleHtml += '<a href="javascript:bind(\'' + row.phoneNum + '\')">修改</a>&nbsp;';
					return handleHtml;
				}
			}, {
				title : '全局身份唯一ID',
				field : 'phoneNum',
				align : 'center',
				width : 95,
				sortable:true
			}, {
				title : '后台登录账户',
				field : 'sysUserId',
				align : 'center',
				width : 100,
				sortable:true
			}, {
				title : '邀请码',
				field : 'inviteCode',
				align : 'center',
				width : 60,
				sortable:true
			}, {
				title : '专属邀请链接',
				field : 'inviteUrl',
				align : 'center',
				width : 400
			},{
				title : '下载链接',
				field : 'downloadUrl',
				align : 'center',
				width : 300
			},{
				title : '用户描述信息',
				field : 'userDesc',
				align : 'center',
				width : 160
			},{
				title : '创建时间',
				field : 'createDate',
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
				title : '更新时间',
				field : 'updateDate',
				align : 'center',
				width : 120,
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
			width:650,
			height:'60%',
			modal:true,
			href:projectName+'/htt/bigUser/admin/toAdd',
			onClose:function(){
				$(this).dialog("destroy");
			},
			buttons:[{
				text:'确定',
			    iconCls:'icon-ok',
			    handler:function(){
				    	$("#bigUserConfigForm").form('submit',{
				    		 type:'POST',
				    		 url : projectName+'/htt/bigUser/admin/add',
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
			width:650,
			height:'60%',
			maximizable:true,
			modal:true,
			href:projectName+'/htt/bigUser/admin/toEdit?phoneNum='+phoneNum,
			onClose:function(){
		    		$(this).dialog("destroy");
		    },
			buttons:[{
					text:'确定',
				    iconCls:'icon-ok',
				    handler:function(){
					    	$("#bigUserConfigForm").form('submit',{
					    		 type:'POST',
					    		 url : projectName+'/htt/bigUser/admin/update',
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
	
	//修改
	function bind(phoneNum){
		win = $("<div></div>").dialog({
			title:'修改',
			width:650,
			height:'60%',
			maximizable:true,
			modal:true,
			href:projectName+'/htt/bigUser/admin/toBind?phoneNum='+phoneNum,
			onClose:function(){
		    		$(this).dialog("destroy");
		    },
			buttons:[{
					text:'确定',
				    iconCls:'icon-ok',
				    handler:function(){
					    	$("#bigUserConfigForm").form('submit',{
					    		 type:'POST',
					    		 url : projectName+'/htt/bigUser/admin/update',
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
			width:650,
			height:'60%',
			maximizable:true,
			modal:true,
			href:projectName+'/htt/bigUser/admin/toEdit?phoneNum='+phoneNum,
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
	
	//刷新
	function reload(){
		$("#datagrid").datagrid("reload");
	}
	
	//搜索
	function doSearch(){
		$("#datagrid").datagrid("load", serializeObject($("#bigUserForm")));
	}
	
	//重置
	function reset(){
		$("#bigUserForm").form("reset");
	}
	