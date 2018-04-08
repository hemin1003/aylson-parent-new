	/**
	 * 
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/htt/httBlacklistHis/admin/list?v_date=' + new Date(),
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
					}else if(value == 3){
						return "人工";
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
	
	//刷新
	function reload(){
		$("#datagrid").datagrid("reload");
	}
	
	//搜索
	function doSearch(){
		$("#datagrid").datagrid("load", serializeObject($("#httBlacklistHisForm")));
	}
	
	//重置
	function reset(){
		$("#httBlacklistHisForm").form("reset");
	}
	