package com.aylson.dc.htt.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
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
import com.aylson.dc.htt.search.HttSysReportInfoSearch;
import com.aylson.dc.htt.service.SysUserReportInfoService;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;

import net.sf.json.JSONObject;

/**
 * 用户统计报表数据查询--弃用
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/sysUserReportInfo")
public class SysUserReportInfoController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(SysUserReportInfoController.class);
	
	@Autowired
	private SysUserReportInfoService sysUserReportInfoService;

	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/sysUserReportInfo/index";
	}
	
	/**
	 * 获取详细列表数据
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttSysReportInfoSearch sysUserReportInfoSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			sysUserReportInfoSearch.setIsPage(true);
			List<HttSysReportInfoList> list = new ArrayList<>();
			Map<String, Object> params = new HashMap<>();
			params.put("currentTime", sysUserReportInfoSearch.getCurrentTime());
			params.put("tomorrowTime", DateUtil2.getCurrentDateByNum(1));
			DecimalFormat dFormat = new DecimalFormat("#0");
			for (int i = 0; i < sysUserReportInfoSearch.getNum(); i++) {
				int j = 0;
				if(i > 0) {
					j = 0 - i;
				}
				HttSysReportInfoList sysUserReportInfo = new HttSysReportInfoList();
				sysUserReportInfo.setDateStr(DateUtil2.getCurrentDateByNum(j));
				String newUserOfDay = this.getResult(j, 101);
				sysUserReportInfo.setNewUserOfDay(newUserOfDay);
				String inviteUserOfDay = this.getResult(j, 102);
				sysUserReportInfo.setInviteUserOfDay(inviteUserOfDay);
				if(newUserOfDay.equals("0")) {
					sysUserReportInfo.setInvitePercentOfDay("0");
				}else {
					double a = (Double.valueOf(inviteUserOfDay) / Double.valueOf(newUserOfDay)) * 100;
					sysUserReportInfo.setInvitePercentOfDay(a==0?"0":dFormat.format(a) + "%");
				}
				sysUserReportInfo.setArticleCountOfDay(this.getResult(j, 104));
				sysUserReportInfo.setReadCountOfDay(this.getResult(j, 105));
				list.add(sysUserReportInfo);
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
	 * 获取顶部三栏数据
	 * @return list
	 */
	@RequestMapping(value = "/admin/listTop", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject listTop(){
		try{
			Map<String, Object> map = new HashMap<>();
			//今日新增用户数
			map.put("todayUser", this.getResult(0, 101));
			//昨日新增用户数
			map.put("yesterdayUser", this.getResult(-1, 101));
			//用户总数
			map.put("allUser", this.getResult(0, 106));
			JSONObject json = JSONObject.fromObject(map);
			return json;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
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
			
			//新增用户数
			list = new ArrayList<>();
			for (int i = 0; i < num; i++) {
				int j = 0;
				if(i > 0) {
					j = 0 - i;
				}
				String count = StrUtil.null2Str(this.getResult(j, 101));
				list.add(count.equals("")?"0":count);
			}
			Collections.reverse(list);
			map.put("value1", list);
			
			//自邀请新增
			list = new ArrayList<>();
			for (int i = 0; i < num; i++) {
				int j = 0;
				if(i > 0) {
					j = 0 - i;
				}
				String count = StrUtil.null2Str(this.getResult(j, 102));
				list.add(count.equals("")?"0":count);
			}
			Collections.reverse(list);
			map.put("value2", list);
			
			//自邀请比例
			DecimalFormat dFormat = new DecimalFormat("#0");
			list = new ArrayList<>();
			for (int i = 0; i < num; i++) {
				int j = 0;
				if(i > 0) {
					j = 0 - i;
				}
				
				String newUserOfDay = StrUtil.null2Str(this.getResult(j, 101));
				String inviteUserOfDay = StrUtil.null2Str(this.getResult(j, 102));
				String count = "0";
				if(!newUserOfDay.equals("0")) {
					double a = (Double.valueOf(inviteUserOfDay) / Double.valueOf(newUserOfDay)) * 100;
					count = (a==0?"0":dFormat.format(a));
				}
				list.add(count.equals("")?"0":count);
			}
			Collections.reverse(list);
			map.put("value3", list);
			
			//阅读文章数
			list = new ArrayList<>();
			for (int i = 0; i < num; i++) {
				int j = 0;
				if(i > 0) {
					j = 0 - i;
				}
				String count = StrUtil.null2Str(this.getResult(j, 104));
				list.add(count.equals("")?"0":count);
			}
			Collections.reverse(list);
			map.put("value4", list);
			
			//文章阅读数
			list = new ArrayList<>();
			for (int i = 0; i < num; i++) {
				int j = 0;
				if(i > 0) {
					j = 0 - i;
				}
				String count = StrUtil.null2Str(this.getResult(j, 105));
				list.add(count.equals("")?"0":count);
			}
			Collections.reverse(list);
			map.put("value5", list);
			
			JSONObject json = JSONObject.fromObject(map);
			return json;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * @param day 0-6
	 * @param type 101=当日新增用户数, 102=自邀请新增, 103=自邀请比例, 104=阅读文章数, 105=文章阅读次数，106=用户总数
	 * @return
	 */
	private String getResult(int day, int type) {
		Map<String, Object> params = new HashMap<>();
		params.put("currentTime", DateUtil2.getCurrentDateByNum(day));
		params.put("tomorrowTime", DateUtil2.getCurrentDateByNum(day+1));
		String result = "";
		switch (type) {
			case 101:
				result = this.sysUserReportInfoService.selectNewUserOfDay(params).getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;
			case 102:
				result = this.sysUserReportInfoService.selectInviteUserOfDay(params).getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;	
			case 103:
				return "100";
			case 104:
				result = this.sysUserReportInfoService.selectArticleCountOfDay(params).getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;
			case 105:
				result = this.sysUserReportInfoService.selectReadCountOfDay(params).getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;
			case 106:
				result = this.sysUserReportInfoService.selectAllUser().getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;
			default:
				throw new RuntimeException("错误类型，请检查。day=" + day + ", type=" + type);
		}
	}
	
	public class HttSysReportInfoList implements Serializable{
		private static final long serialVersionUID = -79583709832163829L;
		
		private String dateStr;
		private String newUserOfDay;
		private String inviteUserOfDay;
		private String invitePercentOfDay;
		private String articleCountOfDay;
		private String readCountOfDay;
		
		public String getInvitePercentOfDay() {
			return invitePercentOfDay;
		}
		public void setInvitePercentOfDay(String invitePercentOfDay) {
			this.invitePercentOfDay = invitePercentOfDay;
		}
		public String getDateStr() {
			return dateStr;
		}
		public void setDateStr(String dateStr) {
			this.dateStr = dateStr;
		}
		public String getNewUserOfDay() {
			return newUserOfDay;
		}
		public void setNewUserOfDay(String newUserOfDay) {
			this.newUserOfDay = newUserOfDay;
		}
		public String getInviteUserOfDay() {
			return inviteUserOfDay;
		}
		public void setInviteUserOfDay(String inviteUserOfDay) {
			this.inviteUserOfDay = inviteUserOfDay;
		}
		public String getArticleCountOfDay() {
			return articleCountOfDay;
		}
		public void setArticleCountOfDay(String articleCountOfDay) {
			this.articleCountOfDay = articleCountOfDay;
		}
		public String getReadCountOfDay() {
			return readCountOfDay;
		}
		public void setReadCountOfDay(String readCountOfDay) {
			this.readCountOfDay = readCountOfDay;
		}
	}
}
