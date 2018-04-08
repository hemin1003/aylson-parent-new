	/**
	 * 用户登录设备历史记录
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/htt/httLoginHis/admin/list?v_date=' + new Date(),
			pagination : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
			fit : true,
			idField : 'id',
			nowrap:false,//允许换行
	        fitColumns:true,//宽度自适应
	        emptyMsg: '<span>无记录</span>',
			singleSelect:true,
			rownumbers: true,
			toolbar:[],
 			frozenColumns : [[{ 
				field : 'opt',
				title : '操作选项',
				align : 'center',
	 			formatter:function(value,row,index){
					var handleHtml = '';
					handleHtml += '<a href="javascript:query(\'' + row.id + '\')">查看更多</a>&nbsp;';
					return handleHtml;
				}
			}, {
				title : '全局身份唯一ID',
				field : 'phoneNum',
				align : 'center',
				sortable:true
			}, {
				title : '设备名',
				field : 'deviceName',
				align : 'center',
				sortable:true
			}, {
				title : 'IMEI码',
				field : 'imei',
				align : 'center',
				sortable:true
			}, {
				title : 'Mac地址',
				field : 'mac',
				align : 'center',
				sortable:true
			}, {
				title : 'Wifi连接名',
				field : 'wifi',
				align : 'center',
				sortable:true
			},{
				title : '经度',
				field : 'longitude',
				align : 'center',
				sortable:true
			},{
				title : '纬度',
				field : 'latitude',
				align : 'center',
				sortable:true
			},{
				title : '位置',
				field : 'position',
				align : 'center',
				sortable:true
			}, {
				title : '客户登录IP',
				field : 'sIp',
				align : 'center',
				sortable:true
			},{
				title : 'Wifi Mac地址',
				field : 'wifiMac',
				align : 'center',
				sortable:true
			},{
				title : '是否Root',
				field : 'isRoot',
				align : 'center',
				sortable:true,
				formatter:function(value,row,index){
					if(value == 0){
						return "否";
					}else if(value == 1){
						return "是";
					}
					return "";
				}
			},{
				title : '渠道号',
				field : 'channel',
				align : 'center',
				sortable:true
			},{
				title : '记录时间',
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
			href:projectName+'/htt/httLoginHis/admin/toAdd',
			onClose:function(){
				$(this).dialog("destroy");
			},
			buttons:[{
				text:'确定',
			    iconCls:'icon-ok',
			    handler:function(){
				    	$("#httLoginHisConfigForm").form('submit',{
				    		 type:'POST',
				    		 url : projectName+'/htt/httLoginHis/admin/add',
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
			width:'50%',
			height:'60%',
			maximizable:true,
			modal:true,
			href:projectName+'/htt/httLoginHis/admin/toEdit?id='+id,
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
			href:projectName+'/htt/httLoginHis/admin/toEdit?id='+id,
			onClose:function(){
		    		$(this).dialog("destroy");
		    },
			buttons:[{
					text:'确定',
				    iconCls:'icon-ok',
				    handler:function(){
					    	$("#httLoginHisConfigForm").form('submit',{
					    		 type:'POST',
					    		 url : projectName+'/htt/httLoginHis/admin/update',
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
					url:projectName+'/htt/httLoginHis/admin/deleteById?id=' + id,
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
					url:projectName+'/htt/httLoginHis/admin/changeStatus?id=' + id+'&status='+status,
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
		$("#datagrid").datagrid("load", serializeObject($("#loginHisForm")));
	}
	
	//重置
	function reset(){
		$("#loginHisForm").form("reset");
	}
	