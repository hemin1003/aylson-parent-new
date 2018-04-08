	/**
	 * 统计爬虫数据查询
	 */
	var datagrid;
	var editor;
	
	$(function() { 
		datagrid = $('#datagrid').datagrid({
			method:'get',
			url : projectName+'/ytt/spiderInfo/admin/list?num=7',
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
				title : '热点',
				field : 'news_hot',
				align : 'center',
				width : 40
			}, {
				title : '社会',
				field : 'news_society',
				align : 'center',
				width : 40
			}, {
				title : '娱乐',
				field : 'news_entertainment',
				align : 'center',
				width : 40
			}, {
				title : '军事',
				field : 'news_military',
				align : 'center',
				width : 40
			}, {
				title : '科技',
				field : 'news_tech',
				align : 'center',
				width : 40
			}, {
				title : '汽车',
				field : 'news_car',
				align : 'center',
				width : 40
			}, {
				title : '体育',
				field : 'news_sports',
				align : 'center',
				width : 40
			}, {
				title : '财经',
				field : 'news_finance',
				align : 'center',
				width : 40
			}, {
				title : '时尚',
				field : 'news_fashion',
				align : 'center',
				width : 40
			}, {
				title : '游戏',
				field : 'news_game',
				align : 'center',
				width : 40
			}, {
				title : '育儿',
				field : 'news_baby',
				align : 'center',
				width : 40
			}, {
				title : '旅游',
				field : 'news_travel',
				align : 'center',
				width : 40
			}, {
				title : '探索',
				field : 'news_discovery',
				align : 'center',
				width : 40
			}, {
				title : '养生',
				field : 'news_regimen',
				align : 'center',
				width : 40
			}, {
				title : '历史',
				field : 'news_history',
				align : 'center',
				width : 40
			}, {
				title : '美食',
				field : 'news_food',
				align : 'center',
				width : 40
			}, {
				title : '故事',
				field : 'news_story',
				align : 'center',
				width : 40
			}, {
				title : '总和',
				field : 'totalNum',
				align : 'center',
				width : 50
			}
			] ]
		});
		
	});
	
	//刷新
	function reload(){
		$("#datagrid").datagrid("reload");
		
		 // 异步加载数据
	      $.get(projectName+'/ytt/spiderInfo/admin/listTop').done(function (data) {
	      		var dataObj = jQuery.parseJSON(data);
	      		$("#todayNewsNum").textbox('setValue', dataObj.todayNewsNum+'');
	      		$("#yesterdayNewsNum").textbox('setValue', dataObj.yesterdayNewsNum+'');
	      });
	}
	
	//搜索
	function doSearch(num){
		getMapData(num);
		//查询参数直接添加在queryParams中     
        $('#datagrid').datagrid({  
        		url : projectName+'/ytt/spiderInfo/admin/list?num=' + num
        })  
        var queryParams =$('#datagrid').datagrid('options').queryParams;  
        getQueryParams(queryParams);  
        $('#datagrid').datagrid('options').queryParams = queryParams;  
       	$("#datagrid").datagrid('reload'); 
	}
	
	//重置
	function reset(){
		$("#spiderInfoForm").form("reset");
	}
	