<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="<%=request.getContextPath()%>/resources/js/echarts/echarts.js"></script>
	<jsp:include page="/resources/inc/meta.jsp"></jsp:include>
	<jsp:include page="/resources/inc/easyui.jsp"></jsp:include>
</head>
<body class="easyui-layout" fit="true">
	<div region="north" style="white-space: nowrap;padding: 5px; height: 100px;">
		<div style="margin-bottom: 5px">
			<form id="spiderInfoForm"  method="post">
				<table class="table_content" border="0" >
					<tr>
						<td class="tar" >今日总新增文章数：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="todayNewsNum" id="todayNewsNum"/>
						</td>
						<td class="tar" >昨日总新增文章数：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="yesterdayNewsNum" id="yesterdayNewsNum"/>
						</td>
						<td class="tar" >总文章数：</td>
						<td class="tal" >
							<input class="easyui-textbox" name="allNewsNum" id="allNewsNum"/>
						</td>
					</tr>
					<tr>
						<td class="tar" colspan="3">
							<br /><br />
							<font size=3 bold>统计新增趋势</font>
						</td> 
					    <td class="tar" colspan="3">
					    		<br /><br />
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="doSearch(7)">7天</a>
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="doSearch(30)">30天</a>
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="doSearch(60)">60天</a>
						</td> 
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center'" border="false" style="overflow: hidden;width:85%;">
    		<div style="width: 100%;height: 50%;">
    			<table id="datagrid"></table>
    		</div>
    		<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    		<div id="main" style="width: 100%; height: 340px;"></div>
	</div>
</body>
<script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>/resources/js/aylson/ytt/spiderInfo.js?date=2016112516"></script>
 <script type="text/javascript">
      // 基于准备好的dom，初始化echarts实例
      var myChart = echarts.init(document.getElementById('main'));

      option = {
      	    title: {
      	        text: ''
      	    },
      	    tooltip: {
      	        trigger: 'axis'
      	    },
      	    legend: {
      	    		data:['热点','社会','娱乐','军事','科技','汽车','体育','财经','时尚','游戏','育儿','旅游','探索','养生','历史','美食','故事']
      	    },
      	    grid: {
      	        left: '3%',
      	        right: '4%',
      	        bottom: '3%',
      	        containLabel: true
      	    },
      	    toolbox: {
      	        feature: {
      	            saveAsImage: {}
      	        }
      	    },
      	    xAxis: {
      	        type: 'category',
      	        boundaryGap: false,
      	        data: []
      	    },
      	    yAxis: {
      	        type: 'value'
      	    },
      	    series: [
      	        {
      	            name:'热点',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'社会',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'娱乐',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'军事',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'科技',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'汽车',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'体育',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'财经',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'时尚',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'游戏',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'育儿',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'旅游',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'探索',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'养生',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'历史',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'美食',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        },
      	        {
      	            name:'故事',
      	            type:'line',
      	            stack: '总量',
      	            data:[]
      	        }
      	    ]
      	};
      
      //获取折图形数据
      function getMapData(num){
    	  	  myChart.showLoading();
       	  // 异步加载数据
          $.get(projectName+'/ytt/spiderInfo/admin/listMap?num=' + num).done(function (data) {
          		myChart.hideLoading();
          		var dataObj = jQuery.parseJSON(data);
    	          // 填入数据
    	          myChart.setOption({
    	              xAxis: {
    	                  data: dataObj.categories
    	              },
    	              series: [{
    	   	            name : '热点',
    	   	            data : dataObj.news_hot
    	   	        },
    	   	        {
    	   	            name : '社会',
    	   	            data : dataObj.news_society
    	   	        },
    	   	        {
    	   	            name : '娱乐',
    	   	            data : dataObj.news_entertainment
    	   	        },
    	   	        {
    	   	            name : '军事',
    	   	            data : dataObj.news_military
    	   	        },
    	   	        {
    	   	            name : '科技',
    	   	            data : dataObj.news_tech
    	   	        },
    	   	        {
    	   	            name : '汽车',
    	   	            data : dataObj.news_car
    	   	        },
    	   	        {
    	   	            name : '体育',
    	   	            data : dataObj.news_sports
    	   	        },
    	   	        {
    	   	            name : '财经',
    	   	            data : dataObj.news_finance
    	   	        },
    	   	        {
    	   	            name : '时尚',
    	   	            data : dataObj.news_fashion
    	   	        },
    	   	        {
    	   	            name : '游戏',
    	   	            data : dataObj.news_game
    	   	        },
    	   	        {
    	   	            name : '育儿',
    	   	            data : dataObj.news_baby
    	   	        },
    	   	        {
    	   	            name : '旅游',
    	   	            data : dataObj.news_travel
    	   	        },
    	   	        {
    	   	            name : '探索',
    	   	            data : dataObj.news_discovery
    	   	        },
    	   	        {
    	   	            name : '养生',
    	   	            data : dataObj.news_regimen
    	   	        },
    	   	        {
    	   	            name : '历史',
    	   	            data : dataObj.news_history
    	   	        },
    	   	        {
    	   	            name : '美食',
    	   	            data : dataObj.news_food
    	   	        },
    	   	        {
    	   	            name : '故事',
    	   	            data : dataObj.news_story
    	   	        }
    	   	        ]
    	          });
          });
      }
   	  
      // 异步加载数据
      $.get(projectName+'/ytt/spiderInfo/admin/listTop').done(function (data) {
      		var dataObj = jQuery.parseJSON(data);
      		$("#todayNewsNum").textbox('setValue', dataObj.todayNewsNum+'');
      		$("#yesterdayNewsNum").textbox('setValue', dataObj.yesterdayNewsNum+'');
      		$("#allNewsNum").textbox('setValue', dataObj.allNewsNum+'');
      });

      getMapData(7);
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
  </script>
</html>