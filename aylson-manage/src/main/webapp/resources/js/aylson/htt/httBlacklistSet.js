	/**
	 * 公告信息记录
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/htt/httBlacklistSet/admin/list?v_date=' + new Date(),
			pagination : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			idField : 'id',
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
				width : 120,
	 			formatter:function(value,row,index){
					var handleHtml = '';
	 				if(row.status == 2){
						handleHtml += '<a href="javascript:changeStatus(\'' + row.id + '\',1)">下线</a>&nbsp;';
						handleHtml += '<a href="javascript:query(\'' + row.id + '\')">查看</a>&nbsp;';
					}else{
						handleHtml += '<a href="javascript:changeStatus(\'' + row.id + '\',2)">上线</a>&nbsp;';
						handleHtml += '<a href="javascript:query(\'' + row.id + '\')">查看</a>&nbsp;';
						handleHtml += '<a href="javascript:edit(\'' + row.id + '\')">修改</a>&nbsp;';
						handleHtml += '<a href="javascript:del(\'' + row.id + '\')">删除</a>&nbsp;';
					}
	 				return handleHtml;	
				}
			}, {
				title : '记录状态',
				field : 'status',
				align : 'center',
				width : 110,
				sortable:true,
				formatter:function(value,row,index){
					if(value == 2){
						return "<font color=green>上线</font>";
					}else if(value == 1){
						return "<font color=red>下线</font>";
					}
					return "<font color=red>下线</font>";
				}
			},{
				title : '字段名',
				field : 'columnName',
				align : 'center',
				width : 110,
				sortable:true
			}, {
				title : '字段说明',
				field : 'columnDesc',
				align : 'center',
				width : 150,
				sortable:true
			}, {
				title : '匹配值',
				field : 'columnValue',
				align : 'center',
				width : 150,
				sortable:true
			}, {
				title : '是否拉黑处理',
				field : 'isBlack',
				align : 'center',
				width : 120,
				sortable:true,
				formatter:function(value,row,index){
					if(value == 0){
						return "否";
					}else if(value == 1){
						return "<font color=red>是</font>";
					}
					return "";
				}
			},{
				title : '创建日期',
				field : 'createDate',
				align : 'center',
				width : 130,
				sortable:true,
				formatter:function(value,row,index){
					if(value){
						return value.substring(0,19);
					}
					return value;
				}
			}, {
				title : '更新日期',
				field : 'updateDate',
				align : 'center',
				width : 130,
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
			width:830,
			height:'70%',
			modal:true,
			href:projectName+'/htt/httBlacklistSet/admin/toAdd',
			onClose:function(){
				$(this).dialog("destroy");
			},
			buttons:[{
				text:'确定',
			    iconCls:'icon-ok',
			    handler:function(){
			    	    //处理富文本编辑的内容
				    	$("#httBlacklistSetConfigForm").form('submit',{
				    		 type:'POST',
				    		 url : projectName+'/htt/httBlacklistSet/admin/add',
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
	
	//查看
	function query(id){
		win = $("<div></div>").dialog({
			title:'查看',
			width:830,
			height:'70%',
			maximizable:true,
			modal:true,
			href:projectName+'/htt/httBlacklistSet/admin/toEdit?id='+id,
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
	
	//修改
	function edit(id){
		win = $("<div></div>").dialog({
			title:'修改',
			width:830,
			height:'70%',
			maximizable:true,
			modal:true,
			href:projectName+'/htt/httBlacklistSet/admin/toEdit?id='+id,
			onClose:function(){
		    		$(this).dialog("destroy");
		    },
			buttons:[{
					text:'确定',
				    iconCls:'icon-ok',
				    handler:function(){
					    	$("#httBlacklistSetConfigForm").form('submit',{
					    		 type:'POST',
					    		 url : projectName+'/htt/httBlacklistSet/admin/update',
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

	//删除
	function del(id){
		$.messager.confirm("提示","确定删除此记录吗？",function(r){
			if(r){
				$.ajax({
					type:"POST",
					url:projectName+'/htt/httBlacklistSet/admin/deleteById?id=' + id,
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
	function changeStatus(id, status){
		var tip = "";
		if(status == 1){
			tip = "确定下线吗？";
			
		}else if(status == 2){
			tip = "确定上线吗？";
		}
		$.messager.confirm("提示",tip,function(r){
			if(r){
				$.ajax({
					type:"POST",
					url:projectName+'/htt/httBlacklistSet/admin/changeStatus?id=' + id+'&status='+status,
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
		$("#datagrid").datagrid("load", serializeObject($("#httBlacklistSetForm")));
	}
	
	//重置
	function reset(){
		$("#httBlacklistSetForm").form("reset");
	}
	