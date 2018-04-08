	/**
	 * 
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/htt/httVideoInfoReport/admin/list?v_date=' + new Date(),
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
				title : '看视频用户',
				field : 'videoUserNum',
				align : 'center',
				width : 110,
				sortable:true
			}, {
				title : '看视频率',
				field : 'videoRate',
				align : 'center',
				width : 150,
				sortable:true
			}, {
				title : '看视频次数',
				field : 'totalVideoNum',
				align : 'center',
				width : 150,
				sortable:true
			}, {
				title : '人均看视频次数',
				field : 'avgVideoNum',
				align : 'center',
				width : 120,
				sortable:true,
			},{
				title : '看视频奖励',
				field : 'totalVideoGold',
				align : 'center',
				sortable:true,
			} ,{
				title : '人均看视频奖励',
				field : 'avgVideoGold',
				align : 'center',
				sortable:true,
			},{
				title : '次均看视频奖励',
				field : 'avgVideoGoldTimes',
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
		$("#datagrid").datagrid("load", serializeObject($("#httVideoInfoReportForm")));
	}
	
	//重置
	function reset(){
		$("#httVideoInfoReportForm").form("reset");
	}
	