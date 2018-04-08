	/**
	 * 提现审核管理
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/htt/httWithdrawHis/admin/list?v_date=' + new Date(),
			pagination : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			idField : 'id',
			singleSelect : false,
			rownumbers: true,
			toolbar:[{
				text:"刷新",
				iconCls : 'icon-reload',
				handler : reload
			}, {
				text:"导出提现申请",
				iconCls : 'icon-add',
				handler : exportApply
			}, {
				text:"导入提现申请",
				iconCls : 'icon-add',
				handler : importApply
			},{
				text:"按日期导出提现申请",
				iconCls:'icon-add',
				handler:exportApplyByDate
			},{
				text:"按提现状态导出提现申请",
				iconCls:'icon-add',
				handler:exportApplyByStatus
			}],
 			frozenColumns : [[{
				field : 'ck',
				checkbox:true,
				align:'center',
				width : 50
			}, { 
				field : 'opt',
				title : '操作选项',
				align : 'center',
				width : 70,
				formatter:function(value,row,index){
					var handleHtml = '';
					handleHtml += '<a href="javascript:edit(\'' + row.id + '\')">处理</a>&nbsp;';
					return handleHtml;
				}
			}, {
				title : '订单流水号',
				field : 'id',
				align : 'center',
				width : 210,
				sortable:true
			}, {
				title : '全局身份唯一ID',
				field : 'phoneNum',
				align : 'center',
				width : 95,
				sortable:true
			}, {
				title : '手机号码',
				field : 'ownPhoneNum',
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
				title : '提现类型',
				field : 'withdrawName',
				align : 'center',
				width : 65,
				sortable:true
			}, {
				title : '姓名',
				field : 'name',
				align : 'center',
				width : 65,
				sortable:true
			}, {
				title : '账户名',
				field : 'account',
				align : 'center',
				width : 200,
				sortable:true
			}, {
				title : '兑换金币',
				field : 'gold',
				align : 'center',
				width : 60,
				sortable:true
			}, {
				title : '提现金额',
				field : 'income',
				align : 'center',
				width : 60,
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
				width : 160,
				formatter:function(value,row,index){
					if(row.statusType == 2 || row.statusType == 3){
						return "<font color=green>" + value +"</font>";
					}else if(row.statusType == 4){
						return "<font color=red>" + value + "</font>";
					}else if(row.statusType == 5){
						return "<font color=green>" + value + "</font>";
					}else if(row.statusType == 6){
						return "<font color=green>" + value + "</font>";
					}else if(row.statusType == 7){
						return "<font color=red>" + value + "</font>";
					}
					return value;
				}
			} , {
				title : '完成时间',
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
				title : '打款失败原因',
				field : 'failMsg',
				align : 'center',
				width : 120
			}
			] ]
		});
	});
	
	//修改
	function edit(id){
		win = $("<div></div>").dialog({
			title:'提现处理',
			width:450,
			height:'60%',
			maximizable:true,
			modal:true,
			href:projectName+'/htt/httWithdrawHis/admin/toEdit?id='+id,
			onClose:function(){
		    		$(this).dialog("destroy");
		    },
			buttons:[{
					text:'提交',
				    iconCls:'icon-ok',
				    handler:function(){
				    		var statusTypeOld = $('#statusTypeOld').val();
				    		var statusType = $('#statusType').val();
				    		if(statusTypeOld == statusType){
				    			$.messager.alert('提示','数据未变化','info');
				    			return;
				    		}
				    		if((statusTypeOld == 2 && statusType ==3) || (statusTypeOld == 3 && statusType ==2)){
				    			$.messager.alert('提示','不能重复审核成功','info');
				    			return;
				    		}
				    		if(statusTypeOld == 4){
				    			$.messager.alert('提示','已失败，系统自动回款了，不能再审核！<font color=blue>APP用户重新发起提现操作即可</font>','info');
				    			return;
				    		}
					    
				    		if(statusType == 1){
				    			$.messager.alert('提示','提交\'提现状态\'不能为\'处理中\'，请审核或取消','error');
				    		}else{
				    			$.messager.confirm("提示","确定更新该记录吗?",function(r){
									if(r){
										$("#httWithdrawHisConfigForm").form('submit',{
									    		 type:'POST',
									    		 url : projectName+'/htt/httWithdrawHis/admin/update',
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
								});
				    		}
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
		$("#datagrid").datagrid("load", serializeObject($("#httWithdrawHisForm")));
	}
	
	//重置
	function reset(){
		$("#httWithdrawHisForm").form("reset");
	}
	
	function exportApply(){
		var rows =  $('#datagrid').datagrid('getChecked');
		if (rows.length == 0){
			$.messager.show({"title":"系统提示","msg":"至少选择一条数据","timeout":1000});
			return;
		}
		var applyIds = [];
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			//处理中
//			if(row.statusType == 1){
				applyIds.push(row.id);
//			}
		}
		window.location = projectName + '/htt/httWithdrawHis/admin/exportApply?applyIds='+ applyIds.join(",");
	}
	
	function importApply(){
		win = $("<div></div>").dialog({
			title:'导入Excel',
			width:650,
			height:'30%',
			maximizable:true,
			modal:true,
			href:projectName+'/htt/httWithdrawHis/admin/toImport',
			onClose:function(){
		    		$(this).dialog("destroy");
		    }
		});
	}
	
	function exportApplyByDate(){
		var withdrawTime = $("#withdrawTime").datebox("getValue");
		if(withdrawTime.length == 0){
			$.messager.show({"title":"系统提示","msg":"请选择日期","timeout":1000});
			return;
		}
		window.location = projectName + '/htt/httWithdrawHis/admin/exportApplyByDate?withdrawTime='+withdrawTime;
	}
	
	function exportApplyByStatus(){
		var statusType = $("#statusType").combobox('getValue');
		if(statusType == 0){
			$.messager.show({"title":"系统提示","msg":"请选择提现状态","timeout":1000});
			return;
		}
		window.location = projectName + '/htt/httWithdrawHis/admin/exportApplyByStatus?statusType='+statusType;
	}
	