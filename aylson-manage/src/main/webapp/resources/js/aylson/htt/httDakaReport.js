	/**
	 * 公告信息记录
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/htt/httDakaReport/admin/list?v_date=' + new Date(),
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
 			frozenColumns : [[ {
				title : '统计日期',
				field : 'date',
				align : 'center',
				width : 110,
				sortable:true,
			},{
				title : '渠道',
				field : 'channel',
				align : 'center',
				width : 110,
				sortable:true,
			},{
				title : '新增注册',
				field : 'newUsers',
				align : 'center',
				width : 110,
				sortable:true
			}, {
				title : '大咖收入（金币数）',
				field : 'allIncome',
				align : 'center',
				width : 150,
				sortable:true
			}, {
				title : '阅读用户比例',
				field : 'readUserRate',
				align : 'center',
				width : 150,
				sortable:true
			}, {
				title : '阅读用户人均阅读时长',
				field : 'avgReadTime',
				align : 'center',
				width : 120,
				sortable:true,
			},{
				title : '邀请率',
				field : 'inviteRate',
				align : 'center',
				sortable:true,
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
		$("#datagrid").datagrid("load", serializeObject($("#httDakaReportForm")));
	}
	
	//重置
	function reset(){
		$("#httDakaReportForm").form("reset");
	}
	