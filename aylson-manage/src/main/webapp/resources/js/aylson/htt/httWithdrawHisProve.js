	/**
	 * 提现审核管理
	 */
	var datagrid;
	var editor;
	var datagrid2;
	var datagrid3;
	var index = 0;
	var maxIndex;
	var id;
	var phoneNumFlag ;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			//method:'get',
			//url : projectName+'/htt/httWithdrawHisProve/admin/listTop',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			idField : 'id',
			singleSelect : false,
			rownumbers: true,
			/*toolbar:[{
				text:"刷新",
				iconCls : 'icon-reload',
				handler : reload
			}],*/
 			frozenColumns : [[{
				title : '订单流水号',
				field : 'id',
				align : 'center',
				width : 220,
				sortable:true
			}, {
				title : '账号',
				field : 'phoneNum',
				align : 'center',
				width : 90,
				sortable:true
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
	
	datagrid2 = $('#datagrid2').datagrid({
		//method:'get',
		//url : projectName+'/htt/httWithdrawHisProve/admin/listTop',
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200],
		fit : true,
		fitColumns : false,
		nowrap : false,
		border : false,
		idField : 'id',
		singleSelect : false,
		rownumbers: true,
		/*toolbar:[{
			text:"刷新",
			iconCls : 'icon-reload',
			handler : reload
		}],*/
		frozenColumns : [[{ 
			field : 'opt',
			title : '操作选项',
			align : 'center',
			width : 100,
 			formatter:function(value,row,index){
				var handleHtml = '';
				handleHtml += '<a href="javascript:query(\'' + row.id + '\')">查看更多</a>&nbsp;';
				return handleHtml;
			}
		}, {
			title : '账号',
			field : 'phoneNum',
			width : 95,
			sortable:true
		}, {
			title : '微信名称',
			field : 'wechatNickname',
			width : 75,
			sortable:true,
		}, {
			title : '手机号',
			field : 'ownPhoneNum',
			width : 70,
			sortable:true,
		}, {
			title : '支付宝账号',
			field : 'alipayAccount',
			width : 100,
			sortable:true,
		}, {
			title : 'imei',
			field : 'imei',
			width : 90,
			sortable:true,
		}, {
			title : '注册日期',
			field : 'registerDate',
			align : 'center',
			width : 110,
			sortable:true,
			formatter:function(value,row,index){
				if(value){
					return value.substring(0,19);
				}
				return value;
			}
		}, {
			title : '账号状态',
			field : 'accountStatus',
			align : 'center',
			width : 55,
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
			title : '徒弟数',
			field : 'students',
			align : 'center',
			width : 45,
			sortable:true,
		}, {
			title : '师傅账号',
			field : 'masterPhoneNum',
			align : 'center',
			width : 95,
			sortable:true,
		}, {
			title : '是否安装作弊器',
			field : 'isCheatedApp',
			align : 'center',
			sortable:true,
			width : 85,
			formatter:function(value,row,index){
				if(value == 1){
					return "<font color=red>是</font>";
				}
				return "";
			}
		} , {
			title : '设备名称',
			field : 'deviceName',
			align : 'center',
			width : 80,
			sortable:true
		}, {
			title : '注册IP',
			field : 'registerIp',
			align : 'center',
			width : 90
		}, {
			title : '内网IP',
			field : 'ip',
			align : 'center',
			width : 90
		}, {
			title : '经纬度',
			field : 'coordinate',
			align : 'center',
			width : 120
		}, {
			title : '地理位置',
			field : 'location',
			align : 'center',
			width : 120
		}, {
			title : 'wifi名称',
			field : 'wifi',
			align : 'center',
			width : 90
		}, {
			title : '数据来源',
			field : 'dataFlag',
			width : 90,
			formatter:function(value,row,index){
				if(value == 1){
					return "师傅数据";
				}else if(value == 2){
					return "徒弟数据";
				}else if(value == 3){
					return "兄弟数据";
				}
				return "未知类型";
			}
		}
		] ]
	});
	
	datagrid3 = $('#datagrid3').datagrid({
		//method:'get',
		//url : projectName+'/htt/httWithdrawHisProve/admin/listTop',
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200],
		fit : true,
		fitColumns : false,
		nowrap : false,
		border : false,
		idField : 'id',
		singleSelect : false,
		rownumbers: true,
		/*toolbar:[{
			text:"刷新",
			iconCls : 'icon-reload',
			handler : reload
		}],*/
		frozenColumns : [[ {
			title : '全局身份唯一ID',
			field : 'phoneNum',
			align : 'center',
			width : 110,
			sortable:true,
		},{
			title : '命中来源',
			field : 'source',
			align : 'center',
			width : 110,
			sortable:true,
			formatter:function(value,row,index){
				if(value == 1){
					return "线上";
				}else if(value == 2){
					return "线下";
				}
				return "";
			}
		},{
			title : '命中策略',
			field : 'strategy',
			align : 'center',
			width : 110,
			sortable:true
		}, {
			title : '账户标记状态',
			field : 'accountStatus',
			align : 'center',
			width : 150,
			sortable:true,
			formatter:function(value,row,index){
				if(value == 0){
					return "正常";
				}else if(value == 1){
					return "疑似";
				}else if(value == 2){
					return "<font color=red>封禁</font>";
				}
				return "";
			}
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
		}
		] ]
	});
	
	//查看
	function query(id){
		win = $("<div></div>").dialog({
			title:'查看',
			width:830,
			height:'75%',
			maximizable:true,
			modal:true,
			href:projectName+'/htt/httWithdrawHisProve/admin/toEdit?id='+id,
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
	function loadData(phoneNum,pid){
		$('#datagrid').datagrid({
			url : projectName+'/htt/httWithdrawHisProve/admin/listTop?phoneNum=' + phoneNum 
		});
		$('#datagrid2').datagrid({
			url : projectName+'/htt/httWithdrawHisProve/admin/listBottom?pid=' + pid 
		});
		$('#datagrid3').datagrid({
			url : projectName+'/htt/httWithdrawHisProve/admin/listBlackListHis?phoneNum=' + phoneNum 
		});
	}
	
	//搜索
	function doSearch(){
		var startTime = $("#startDate").datebox("getValue");
		var endTime = $("#endDate").datebox("getValue"); 
		if(startTime.length == 0){
			$.messager.show({"title":"系统提示","msg":"请选择开始日期！","timeout":1000});
		}else if(endTime.length == 0){
			$.messager.show({"title":"系统提示","msg":"请选择结束日期！","timeout":1000});
		}
		if(startTime.length != 0 && endTime.length != 0){
			if($("#isSuspect").is(':checked')){
				loadTop(startTime,endTime,'1');
			}else{
				loadTop(startTime,endTime,'0');
			}
			$("#currentPage").html('1'); 
			index=0;
		}
	}
	
	//重置
	function reset(){
		$("#httWithdrawHisProveForm").form("reset");
	}
	
	function clickNext() {
		if(index < maxIndex-1){
			index++;
			loadNext(index);	
			$("#currentPage").html(index + 1); 
		} else {
			$.messager.show({"title":"系统提示","msg":"最后一条啦！","timeout":1000});
		}
	}
	
	function clickLast() {
		if(index > 0){
			index--;
			loadNext(index);	
			$("#currentPage").html(index + 1); 
		}
		else {
			$.messager.show({"title":"系统提示","msg":"最后一条啦！","timeout":1000});
		}
	}
	
	//弹出加载层
	function msgLoad() {  
	    $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
	    $("<div class=\"datagrid-mask-msg\"></div>").html("数据加载中，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });  
	}
	//取消加载层  
	function msgDisLoad() {  
	    $(".datagrid-mask").remove();  
	    $(".datagrid-mask-msg").remove();  
	}
	
	function loadTop(startDate,endDate,isSuspect) {
		msgLoad();
		//异步加载数据
		$.get(projectName+'/htt/httWithdrawHisProve/admin/listInfo?startDate='+ startDate+'&endDate='+endDate+'&isSuspect='+ isSuspect, function (data) {
				if(!data){
					$("#httWithdrawHisDataForm").form("reset");
					$('#datagrid').datagrid('loadData',{total:0,rows:[]});
					$('#datagrid2').datagrid('loadData',{total:0,rows:[]});
					$('#datagrid3').datagrid('loadData',{total:0,rows:[]});
					$("#currentPage").html('0'); 
					$("#totalPage").html('0');
					loadData("forClear", "forClear");
					$.messager.show({"title":"系统提示","msg":"该时间段没有数据","timeout":1000});
					maxIndex = 0;
				}else{
					var dataObj = jQuery.parseJSON(data);
					fillData(dataObj);
					maxIndex = dataObj.totalPage;
					$("#totalPage").html(maxIndex);
				}
				msgDisLoad();
		});
	} 
	
	function loadNext(index) {
		msgLoad();
		//异步加载数据
		$.get(projectName+'/htt/httWithdrawHisProve/admin/listNext?index='+ index).done(function (data) {
				var dataObj = jQuery.parseJSON(data);
				fillData(dataObj);
				msgDisLoad();
		});
	} 
	
	function fillData(dataObj){
		$("#phoneNum").textbox('setValue', dataObj.phoneNum);
		$("#userPwd").textbox('setValue', dataObj.userPwd);
		$("#userPwdCount").textbox('setValue', dataObj.userPwdCount);
		$("#channel").textbox('setValue', dataObj.channel);
		$("#deviceName").textbox('setValue', dataObj.deviceName);
		$("#deviceNameCount").textbox('setValue', dataObj.deviceNameCount);
		$("#wechatNickname").textbox('setValue', dataObj.wechatNickname);
		$("#registerIp").textbox('setValue', dataObj.registerIp);
		$("#registerIpCount").textbox('setValue', dataObj.registerIpCount);
		$("#ownPhoneNum").textbox('setValue', dataObj.ownPhoneNum);
		$("#ip").textbox('setValue', dataObj.ip);
		$("#ipCount").textbox('setValue', dataObj.ipCount);
		$("#alipayAccount").textbox('setValue', dataObj.alipayAccount);
		$("#coordinate").textbox('setValue', dataObj.coordinate);
		$("#coordinateCount").textbox('setValue', dataObj.coordinateCount);
		$("#imei").textbox('setValue', dataObj.imei);
		$("#location").textbox('setValue', dataObj.location);
		$("#locationCount").textbox('setValue', dataObj.locationCount);
		$("#registerDate").textbox('setValue', dataObj.registerDate);
		$("#wifiMac").textbox('setValue', dataObj.wifiMac);
		$("#wifiMacCount").textbox('setValue', dataObj.wifiMacCount);
		if(dataObj.accountStatus == 0){
			$("#accountStatus").textbox('setValue', '正常');
		}else if(dataObj.accountStatus == 1){
			$("#accountStatus").textbox('setValue', '疑似');
		}else if(dataObj.accountStatus == 2){
			$("#accountStatus").textbox('setValue', '封禁');
		}
		$("#installedListMdCode").textbox('setValue', dataObj.installedListMdCode);
		$("#installedListMdCodeCount").textbox('setValue', dataObj.installedListMdCodeCount);
		$("#isCheatedApp").textbox('setValue', dataObj.isCheatedApp);
		$("#masterPhoneNum").textbox('setValue', dataObj.masterPhoneNum);
		$("#students").textbox('setValue', dataObj.students);
		phoneNumFlag = dataObj.phoneNum;
		id = dataObj.id;
		loadData(dataObj.phoneNum, dataObj.id);
	}
	
	function doPass() {
		var phoneNum = $("#phoneNum").textbox("getValue");
		if(!phoneNum){
			$.messager.show({"title":"系统提示","msg":"无数据","timeout":1000});
		}else{
			$.messager.confirm("提示","确定通过吗？",function(r){
				if(r){
					if(maxIndex != 0){
						updateStatus(1);
					}
				}
			});
		}
	}
	
	function doReject() {
		var phoneNum = $("#phoneNum").textbox("getValue");
		if(!phoneNum){
			$.messager.show({"title":"系统提示","msg":"无数据","timeout":1000});
		}else{
			$.messager.confirm("提示","确定拒绝吗？",function(r){
				if(r){
					if(maxIndex != 0){
						updateStatus(2);
					}
				}
			});
		}
	}
	
	function doRejectAndBlackList() {
		var phoneNum = $("#phoneNum").textbox("getValue");
		if(!phoneNum){
			$.messager.show({"title":"系统提示","msg":"无数据","timeout":1000});
		}else{
			$.messager.confirm("提示","确定拒绝并拉黑吗？",function(r){
				if(r){
					if(maxIndex != 0){
						updateStatus(3);
					}
				}
			});
		}
	}
	
	function updateStatus(status){
		msgLoad();
		//异步加载数据
		//status=1 表示通过
		//status=2表示拒绝
		//status=3表示拒绝并加入黑名单
		$.get(projectName+'/htt/httWithdrawHisProve/admin/updateWithdrawStatus?phoneNum='+ phoneNumFlag +'&status='+status+'&id=' + id).done(function (data) {
				var dataObj = jQuery.parseJSON(data);
				if(dataObj.success == 0){
					$.messager.show({"title":"系统提示","msg":"操作成功！","timeout":1000});
					$("#datagrid").datagrid("reload");
					doSearch();
				}else if (dataObj.success == 1) {
					$.messager.show({"title":"系统提示","msg":"操作失败！","timeout":1000});
				}else{
					$.messager.show({"title":"系统提示","msg":dataObj.result,"timeout":1000});
				}
				msgDisLoad();
		});
	}