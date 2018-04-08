	/**
	 * 支出统计报表数据查询
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/htt/expenseReportInfo/admin/list?num=30',
			pagination : true,
			pageSize : 30,
			pageList : [ 30, 60, 90 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			idField : 'dateStr',
			singleSelect:true,
			rownumbers: true,
			toolbar:[{
				text:"详细列表"
			},{
				text:"刷新",
				iconCls : 'icon-reload',
				handler : reload
			}],
 			frozenColumns : [[ {
				title : '日期',
				field : 'dateStr',
				align : 'center',
				width : 80
			}, {
				title : '新增（注册用户）',
				field : 'newUser',
				align : 'center',
				width : 110
			}, {
				title : '全部邀请奖励',
				field : 'allInviteAward',
				align : 'center',
				width : 110
			}, {
				title : '全部阅读奖励',
				field : 'allReadAward',
				align : 'center',
				width : 110
			}, {
				title : '全部其他奖励',
				field : 'allOtherAward',
				align : 'center',
				width : 110
			}, {
				title : '全部金币奖励',
				field : 'allAward',
				align : 'center',
				width : 110
			}, {
				title : '支付宝申请提现金额',
				field : 'userWithdrawOfAlipay',
				align : 'center',
				width : 120
			}, {
				title : '支付宝成功打款金额',
				field : 'successWithdrawOfAlipay',
				align : 'center',
				width : 120
			}, {
				title : '微信申请提现金额',
				field : 'userWithdrawOfWechat',
				align : 'center',
				width : 120
			}, {
				title : '微信成功打款金额',
				field : 'successWithdrawOfWechat',
				align : 'center',
				width : 120
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
			href:projectName+'/htt/expenseReportInfo/admin/toAdd',
			onClose:function(){
				$(this).dialog("destroy");
			},
			buttons:[{
				text:'确定',
			    iconCls:'icon-ok',
			    handler:function(){
				    	$("#expenseReportInfoConfigForm").form('submit',{
				    		 type:'POST',
				    		 url : projectName+'/htt/expenseReportInfo/admin/add',
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
	function edit(id){
		win = $("<div></div>").dialog({
			title:'修改',
			width:450,
			height:'60%',
			maximizable:true,
			modal:true,
			href:projectName+'/htt/expenseReportInfo/admin/toEdit?id='+id,
			onClose:function(){
		    		$(this).dialog("destroy");
		    },
			buttons:[{
					text:'确定',
				    iconCls:'icon-ok',
				    handler:function(){
					    	$("#expenseReportInfoConfigForm").form('submit',{
					    		 type:'POST',
					    		 url : projectName+'/htt/expenseReportInfo/admin/update',
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
					url:projectName+'/htt/expenseReportInfo/admin/deleteById?id=' + id,
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
	function doSearch(num){
		getMapData(num);
		//查询参数直接添加在queryParams中
		 $('#datagrid').datagrid({  
        		url : projectName+'/htt/expenseReportInfo/admin/list?num=' + num
        })  
        var queryParams =$('#datagrid').datagrid('options').queryParams;  
        getQueryParams(queryParams);  
        $('#datagrid').datagrid('options').queryParams = queryParams;  
       	$("#datagrid").datagrid('reload');
	}
	
	//重置
	function reset(){
		$("#expenseReportInfoForm").form("reset");
	}
	