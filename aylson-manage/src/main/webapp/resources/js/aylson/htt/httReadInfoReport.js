	/**
	 * 
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/htt/httReadInfoReport/admin/list?v_date=' + new Date(),
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
				title : '日期',
				field : 'satisticDate',
				align : 'center',
				width : 110,
				sortable:true,
			},{
				title : '活跃用户',
				field : 'activeUserNum',
				align : 'center',
				width : 110,
				sortable:true,
			},{
				title : '阅读用户',
				field : 'readUserNum',
				align : 'center',
				width : 110,
				sortable:true
			}, {
				title : '阅读率',
				field : 'readRate',
				align : 'center',
				width : 150,
				sortable:true
			}, {
				title : '阅读次数',
				field : 'totalReadNum',
				align : 'center',
				width : 150,
				sortable:true
			}, {
				title : '人均阅读次数',
				field : 'avgReadNum',
				align : 'center',
				width : 120,
				sortable:true,
			},{
				title : '阅读奖励',
				field : 'totalReadGold',
				align : 'center',
				sortable:true,
			} ,{
				title : '人均阅读奖励',
				field : 'avgReadGold',
				align : 'center',
				sortable:true,
			},{
				title : '次均阅读奖励',
				field : 'avgReadGoldTimes',
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
		$("#datagrid").datagrid("load", serializeObject($("#httReadInfoReportForm")));
	}
	
	//重置
	function reset(){
		$("#httReadInfoReportForm").form("reset");
	}
	