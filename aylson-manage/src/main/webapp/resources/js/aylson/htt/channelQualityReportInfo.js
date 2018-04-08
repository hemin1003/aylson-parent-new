	/**
	 * 公告信息记录
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/htt/channelQualityReportInfo/admin/list?v_date=' + new Date(),
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
				title : '注册日期',
				field : 'registerDate',
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
				title : '阅读文章总数',
				field : 'totalReadingTimes',
				align : 'center',
				width : 150,
				sortable:true
			}, {
				title : '人均阅读文章数',
				field : 'avgReadingTimes',
				align : 'center',
				width : 150,
				sortable:true
			}, {
				title : '人均阅读时长（分钟）',
				field : 'avgReadingMinute',
				align : 'center',
				width : 120,
				sortable:true,
			},{
				title : '真新增注册',
				field : 'realNewUsers',
				align : 'center',
				sortable:true,
			},{
				title : '阅读比例',
				field : 'readRatio',
				align : 'center',
				sortable:true,
			} ,{
				title : '累计奖励大于1元用户比例',
				field : 'totalGoldMoreThanOneRatio',
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
		$("#datagrid").datagrid("load", serializeObject($("#channelQualityReportInfoForm")));
	}
	
	//重置
	function reset(){
		$("#channelQualityReportInfoForm").form("reset");
	}
	