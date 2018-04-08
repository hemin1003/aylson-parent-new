	/**
	 * 爬虫信息配置
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/ytt/spiderConfig/admin/list?v_date=' + new Date(),
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
					handleHtml += '<a href="javascript:query(\'' + row.id + '\')">查看</a>&nbsp;';
					handleHtml += '<a href="javascript:edit(\'' + row.id + '\')">修改</a>&nbsp;';
					return handleHtml;
				}
			}, {
				title : '域名标识',
				field : 'domain',
				align : 'center',
				width : 120,
				sortable:true
			}, {
				title : '域名名称',
				field : 'domainName',
				align : 'center',
				width : 100,
				sortable:true
			}, {
				title : '报警阀值',
				field : 'alpha',
				align : 'center',
				width : 80,
				sortable:true
			}, {
				title : '并发请求数',
				field : 'reqCount',
				align : 'center',
				width : 80,
				sortable:true
			}, {
				title : '并发处理线程数',
				field : 'threadCount',
				align : 'center',
				width : 100,
				sortable:true
			}, {
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
			}
			, {
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
			width:450,
			height:'50%',
			modal:true,
			href:projectName+'/ytt/spiderConfig/admin/toAdd',
			onClose:function(){
				$(this).dialog("destroy");
			},
			buttons:[{
				text:'确定',
			    iconCls:'icon-ok',
			    handler:function(){
				    	$("#spiderConfigForm").form('submit',{
				    		 type:'POST',
				    		 url : projectName+'/ytt/spiderConfig/admin/add',
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
			width:450,
			height:'50%',
			maximizable:true,
			modal:true,
			href:projectName+'/ytt/spiderConfig/admin/toEdit?id='+id,
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
			width:450,
			height:'50%',
			maximizable:true,
			modal:true,
			href:projectName+'/ytt/spiderConfig/admin/toEdit?id='+id,
			onClose:function(){
		    		$(this).dialog("destroy");
		    },
			buttons:[{
					text:'确定',
				    iconCls:'icon-ok',
				    handler:function(){
					    	$("#spiderConfigForm").form('submit',{
					    		 type:'POST',
					    		 url : projectName+'/ytt/spiderConfig/admin/update',
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
					url:projectName+'/ytt/spiderConfig/admin/deleteById?id=' + id,
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
		$("#datagrid").datagrid("load", serializeObject($("#spiderConfigForm")));
	}
	
	//重置
	function reset(){
		$("#spiderConfigForm").form("reset");
	}
	