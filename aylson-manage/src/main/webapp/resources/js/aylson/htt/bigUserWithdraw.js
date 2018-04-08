	/**
	 * 大咖提现
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/htt/bigUserWithdraw/admin/list?v_date=' + new Date(),
			pagination : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 500 ],
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
			}, {
				text:"提现申请",
				iconCls : 'icon-add',
				handler : apply
			}],
 			frozenColumns : [[{
				title : '订单流水号',
				field : 'id',
				align : 'center',
				width : 210,
				sortable:true
			}, {
				title : '手机号码',
				field : 'phoneNum',
				align : 'center',
				width : 120,
				sortable:true
			}, {
				title : '提现类型',
				field : 'withdrawName',
				align : 'center',
				width : 80,
				sortable:true
			}, {
				title : '姓名',
				field : 'name',
				align : 'center',
				width : 90,
				sortable:true
			}, {
				title : '账户名',
				field : 'account',
				align : 'center',
				width : 150,
				sortable:true
			}, {
				title : '兑换金币',
				field : 'gold',
				align : 'center',
				width : 70,
				sortable:true
			}, {
				title : '提现金额',
				field : 'income',
				align : 'center',
				width : 70,
				sortable:true
			}, {
				title : '提现发起时间',
				field : 'withdrawTime',
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
				title : '提现状态',
				field : 'status',
				align : 'center',
				sortable:true,
				width : 80,
				formatter:function(value,row,index){
					if(row.statusType == 2 || row.statusType == 3){
						return "<font color=green>提现成功</font>";
					}else if(row.statusType == 4){
						return "<font color=red>提现失败</font>";
					}else if(row.statusType == 5){
						return "<font color=green>审核中</font>";
					}else if(row.statusType == 6){
						return "<font color=green>审核成功</font>";
					}else if(row.statusType == 7){
						return "<font color=red>审核失败</font>";
					}
					return value;
				}
			} , {
				title : '审批完成时间',
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
			}
			] ]
		});
		
	});
	
	//提现申请
	function apply(){
		win = $("<div></div>").dialog({
			title:'提现申请',
			width:450,
			height:'60%',
			maximizable:true,
			modal:true,
			href:projectName+'/htt/bigUserWithdraw/admin/toAdd',
			onClose:function(){
		    		$(this).dialog("destroy");
		    },
			buttons:[{
					text:'提交',
				    iconCls:'icon-ok',
				    handler:function(){
					    	$("#bigUserWithdrawConfigForm").form('submit',{
					    		 type:'POST',
					    		 url : projectName+'/htt/bigUserWithdraw/admin/add',
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
	
	//刷新
	function reload(){
		$("#datagrid").datagrid("reload");
	}
	
	//搜索
	function doSearch(){
		$("#datagrid").datagrid("load", serializeObject($("#bigUserWithdrawForm")));
	}
	
	//重置
	function reset(){
		$("#bigUserWithdrawForm").form("reset");
	}
	