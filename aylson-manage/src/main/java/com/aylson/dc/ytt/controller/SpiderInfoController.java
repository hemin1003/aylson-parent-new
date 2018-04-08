package com.aylson.dc.ytt.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aylson.core.easyui.EasyuiDataGridJson;
import com.aylson.core.frame.controller.BaseController;
import com.aylson.dc.ytt.po.SpiderInfo;
import com.aylson.dc.ytt.search.SpiderInfoSearch;
import com.aylson.dc.ytt.service.SpiderInfoService;
import com.aylson.dc.ytt.vo.SpiderInfoVo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;

import net.sf.json.JSONObject;

/**
 * 统计爬虫数据查询
 * @author Minbo
 */
@Controller
@RequestMapping("/ytt/spiderInfo")
public class SpiderInfoController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(SpiderInfoController.class);
	
	@Autowired
	private SpiderInfoService SpiderInfoService;

	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/ytt/admin/spiderInfo/index";
	}
	
	/**
	 * 获取详细列表数据
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(SpiderInfoSearch SpiderInfoSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			SpiderInfoSearch.setIsPage(true);
			List<Object> list = new ArrayList<>();
			Map<String, Object> params = new HashMap<>();
			params.put("currentTime", SpiderInfoSearch.getCurrentTime());
			for (int i = 0; i < SpiderInfoSearch.getNum(); i++) {
				int j = 0;
				if(i > 0) {
					j = 0 - i;
				}
				SpiderInfoList SpiderInfo = new SpiderInfoList();
				SpiderInfo.setDateStr(DateUtil2.getCurrentDateByNum(j));
				SpiderInfoSearch.setCurrentTime(SpiderInfo.getDateStr());
				List<SpiderInfoVo> spiderInfoList = this.SpiderInfoService.getList(SpiderInfoSearch);
				int totalNum = 0; 
				for (SpiderInfoVo spiderInfoVo : spiderInfoList) {
					totalNum += spiderInfoVo.getNewsNum();
					if(spiderInfoVo.getDomain().equals("news_hot")){
						SpiderInfo.setNews_hot(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_society")) {
						SpiderInfo.setNews_society(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_entertainment")) {
						SpiderInfo.setNews_entertainment(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_military")) {
						SpiderInfo.setNews_military(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_tech")) {
						SpiderInfo.setNews_tech(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_car")) {
						SpiderInfo.setNews_car(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_sports")) {
						SpiderInfo.setNews_sports(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_finance")) {
						SpiderInfo.setNews_finance(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_fashion")) {
						SpiderInfo.setNews_fashion(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_game")) {
						SpiderInfo.setNews_game(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_baby")) {
						SpiderInfo.setNews_baby(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_travel")) {
						SpiderInfo.setNews_travel(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_discovery")) {
						SpiderInfo.setNews_discovery(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_regimen")) {
						SpiderInfo.setNews_regimen(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_history")) {
						SpiderInfo.setNews_history(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_food")) {
						SpiderInfo.setNews_food(String.valueOf(spiderInfoVo.getNewsNum()));
					}else if(spiderInfoVo.getDomain().equals("news_story")) {
						SpiderInfo.setNews_story(String.valueOf(spiderInfoVo.getNewsNum()));
					}
				}
				SpiderInfo.setTotalNum(totalNum);
				list.add(SpiderInfo);
			}
			result.setTotal(Long.valueOf(list.size()));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 获取顶部两栏数据
	 * @return list
	 */
	@RequestMapping(value = "/admin/listTop", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject listTop(){
		try{
			Map<String, Object> map = new HashMap<>();
			//今日总新增文章数
			Map<String, Object> params = new HashMap<>();
			params.put("currentTime", DateUtil2.getCurrentDateByNum(0));
			map.put("todayNewsNum", this.SpiderInfoService.selectDaysOfNewsNum(params));
			//昨日总新增文章数
			params = new HashMap<>();
			params.put("currentTime", DateUtil2.getCurrentDateByNum(-1));
			map.put("yesterdayNewsNum", this.SpiderInfoService.selectDaysOfNewsNum(params));
			map.put("allNewsNum", this.SpiderInfoService.selectAllNewsNum());
			JSONObject json = JSONObject.fromObject(map);
			return json;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static String[] tagList = new String[] { "news_hot", "news_society", "news_entertainment", "news_military",
			"news_tech", "news_car", "news_sports", "news_finance", "news_fashion", "news_game", "news_baby",
			"news_travel", "news_discovery", "news_regimen", "news_history", "news_food", "news_story" };
	
	/**
	 * 获取折线图数据
	 * @return list
	 */
	@RequestMapping(value = "/admin/listMap", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject listMap(int num){
		try{
			Map<String, Object> map = new HashMap<>();
			//横坐标，统计七天的数据，含今天
			List<Object> list = new ArrayList<>();
			for (int i = 0; i < num; i++) {
				int j = 0;
				if(i > 0) {
					j = 0 - i;
				}
				list.add(DateUtil2.getCurrentDateByNum(j));
			}
			Collections.reverse(list);
			map.put("categories", list);
			//各类型tag数据
			for (int i = 0; i < tagList.length; i++) {
				String tag = tagList[i];
				list = new ArrayList<>();
				list = this.graphData(tag, num);
				map.put(tag, list);
			}
			JSONObject json = JSONObject.fromObject(map);
			return json;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 获取tag类型的数据
	 * @param domain
	 * @param limit
	 * @return
	 */
	private List<Object> graphData(String domain, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("domain", domain);
		params.put("limit", limit);
		List<SpiderInfo> newsHotList = this.SpiderInfoService.selectNewsNumOfTag(params);
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < limit; i++) {
			int j = 0;
			if(i > 0) {
				j = 0 - i;
			}
			String currentTime = DateUtil2.getCurrentDateByNum(j);
			boolean flag = false;
			for (SpiderInfo spiderInfo : newsHotList) {
				if(spiderInfo.getDate().equals(currentTime)) {
					String count = StrUtil.null2Str(spiderInfo.getNewsNum());
					list.add(count.equals("")?"0":count);
					flag = true;
					break;
				}
			}
			//如果没有匹配，则为0
			if(!flag) {
				list.add("0");
			}
		}
		Collections.reverse(list);
		return list;
	}
	
	public class SpiderInfoList implements Serializable{
		
		private static final long serialVersionUID = -79583709832163829L;
		
		private String dateStr;
		private String news_hot;
		private String news_society;
		private String news_entertainment;
		private String news_military;
		private String news_tech;
		private String news_car;
		private String news_sports;
		private String news_finance;
		private String news_fashion;
		private String news_game;
		private String news_baby;
		private String news_travel;
		private String news_discovery;
		private String news_regimen;
		private String news_history;
		private String news_food;
		private String news_story;
		private Integer totalNum;

		public String getDateStr() {
			return dateStr;
		}
		public void setDateStr(String dateStr) {
			this.dateStr = dateStr;
		}
		public String getNews_hot() {
			return news_hot;
		}
		public void setNews_hot(String news_hot) {
			this.news_hot = news_hot;
		}
		public String getNews_society() {
			return news_society;
		}
		public void setNews_society(String news_society) {
			this.news_society = news_society;
		}
		public String getNews_entertainment() {
			return news_entertainment;
		}
		public void setNews_entertainment(String news_entertainment) {
			this.news_entertainment = news_entertainment;
		}
		public String getNews_military() {
			return news_military;
		}
		public void setNews_military(String news_military) {
			this.news_military = news_military;
		}
		public String getNews_tech() {
			return news_tech;
		}
		public void setNews_tech(String news_tech) {
			this.news_tech = news_tech;
		}
		public String getNews_car() {
			return news_car;
		}
		public void setNews_car(String news_car) {
			this.news_car = news_car;
		}
		public String getNews_sports() {
			return news_sports;
		}
		public void setNews_sports(String news_sports) {
			this.news_sports = news_sports;
		}
		public String getNews_finance() {
			return news_finance;
		}
		public void setNews_finance(String news_finance) {
			this.news_finance = news_finance;
		}
		public String getNews_fashion() {
			return news_fashion;
		}
		public void setNews_fashion(String news_fashion) {
			this.news_fashion = news_fashion;
		}
		public String getNews_game() {
			return news_game;
		}
		public void setNews_game(String news_game) {
			this.news_game = news_game;
		}
		public String getNews_baby() {
			return news_baby;
		}
		public void setNews_baby(String news_baby) {
			this.news_baby = news_baby;
		}
		public String getNews_travel() {
			return news_travel;
		}
		public void setNews_travel(String news_travel) {
			this.news_travel = news_travel;
		}
		public String getNews_discovery() {
			return news_discovery;
		}
		public void setNews_discovery(String news_discovery) {
			this.news_discovery = news_discovery;
		}
		public String getNews_regimen() {
			return news_regimen;
		}
		public void setNews_regimen(String news_regimen) {
			this.news_regimen = news_regimen;
		}
		public String getNews_history() {
			return news_history;
		}
		public void setNews_history(String news_history) {
			this.news_history = news_history;
		}
		public String getNews_food() {
			return news_food;
		}
		public void setNews_food(String news_food) {
			this.news_food = news_food;
		}
		public String getNews_story() {
			return news_story;
		}
		public void setNews_story(String news_story) {
			this.news_story = news_story;
		}
		public Integer getTotalNum() {
			return totalNum;
		}
		public void setTotalNum(Integer totalNum) {
			this.totalNum = totalNum;
		}
	}
}