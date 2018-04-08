package com.aylson.dc.htt.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.aylson.dc.htt.service.SysExpenseReportInfoService;
import com.aylson.dc.htt.vo.SysExpenseReportInfoVo;
import com.aylson.utils.DateUtil2;

import net.sf.json.JSONObject;

/**
 * 支出统计报表数据查询-弃用
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/sysExpenseReportInfo")
public class SysExpenseReportInfoController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(SysExpenseReportInfoController.class);
	
	@Autowired
	private SysExpenseReportInfoService sysExpenseReportInfoService;

	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/sysExpenseReportInfo/index";
	}
	
	/**
	 * 获取详细列表数据
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttSysReportInfoSearch sysExpenseReportInfoSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			sysExpenseReportInfoSearch.setIsPage(true);
			List<HttSysReportInfoList> list = new ArrayList<>();
			Map<String, Object> params = new HashMap<>();
			params.put("currentTime", sysExpenseReportInfoSearch.getCurrentTime());
			for (int i = 0; i < sysExpenseReportInfoSearch.getNum(); i++) {
//				int j = 0;
//				if(i > 0) {
//					j = 0 - i;
//				}
//				HttSysReportInfoList sysExpenseReportInfo = new HttSysReportInfoList();
//				sysExpenseReportInfo.setDateStr(DateUtil2.getCurrentDateByNum(j));;
//				sysExpenseReportInfo.setNewUserOfDay(this.getResult(j, 101));
//				sysExpenseReportInfo.setAllInviteAwardOfDay(this.getResult(j, 102));
//				sysExpenseReportInfo.setAllReadAwardOfDay(this.getResult(j, 103));
//				sysExpenseReportInfo.setAllOtherAwardOfDay(this.getResult(j, 104));
//				sysExpenseReportInfo.setAllAwardOfDay(this.getResult(j, 108));
//				sysExpenseReportInfo.setUserWithdrawOfDay(this.getResult(j, 105));
//				sysExpenseReportInfo.setSuccessWithdrawOfDay(this.getResult(j, 106));
//				list.add(sysExpenseReportInfo);
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
			//今日用户金币
			map.put("todayGold", this.getResult(0, 108));
			//昨日用户金币
			map.put("yesterdayGold", this.getResult(-1, 108));
			//用户总金币
			map.put("allGold", this.getResult(0, 107));
			//预计总支出金额
			map.put("preSuccessWithdraw", this.getResult(0, 109));
			//实际已支出总金额
			map.put("allSuccessWithdraw", this.getResult(0, 110));
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
//			//横坐标，统计七天的数据，含今天
//			List<Object> list = new ArrayList<>();
//			for (int i = 0; i < num; i++) {
//				int j = 0;
//				if(i > 0) {
//					j = 0 - i;
//				}
//				list.add(DateUtil2.getCurrentDateByNum(j));
//			}
//			Collections.reverse(list);
//			map.put("categories", list);
//			
//			//新增用户数
//			list = new ArrayList<>();
//			for (int i = 0; i < num; i++) {
//				int j = 0;
//				if(i > 0) {
//					j = 0 - i;
//				}
//				String count = StrUtil.null2Str(this.getResult(j, 101));
//				list.add(count.equals("")?"0":count);
//			}
//			Collections.reverse(list);
//			map.put("value1", list);
//			
//			//全部邀请奖励
//			list = new ArrayList<>();
//			for (int i = 0; i < num; i++) {
//				int j = 0;
//				if(i > 0) {
//					j = 0 - i;
//				}
//				String count = StrUtil.null2Str(this.getResult(j, 102));
//				list.add(count.equals("")?"0":count);
//			}
//			Collections.reverse(list);
//			map.put("value2", list);
//			
//			//全部阅读奖励
//			list = new ArrayList<>();
//			for (int i = 0; i < num; i++) {
//				int j = 0;
//				if(i > 0) {
//					j = 0 - i;
//				}
//				String count = StrUtil.null2Str(this.getResult(j, 103));
//				list.add(count.equals("")?"0":count);
//			}
//			Collections.reverse(list);
//			map.put("value3", list);
//			
//			//全部其他奖励
//			list = new ArrayList<>();
//			for (int i = 0; i < num; i++) {
//				int j = 0;
//				if(i > 0) {
//					j = 0 - i;
//				}
//				String count = StrUtil.null2Str(this.getResult(j, 104));
//				list.add(count.equals("")?"0":count);
//			}
//			Collections.reverse(list);
//			map.put("value4", list);
//			
//			//全部金币奖励
//			list = new ArrayList<>();
//			for (int i = 0; i < num; i++) {
//				int j = 0;
//				if(i > 0) {
//					j = 0 - i;
//				}
//				String count = StrUtil.null2Str(this.getResult(j, 108));
//				list.add(count.equals("")?"0":count);
//			}
//			Collections.reverse(list);
//			map.put("value5", list);
//			
//			//申请提现金额
//			list = new ArrayList<>();
//			for (int i = 0; i < num; i++) {
//				int j = 0;
//				if(i > 0) {
//					j = 0 - i;
//				}
//				String count = StrUtil.null2Str(this.getResult(j, 105));
//				list.add(count.equals("")?"0.0":count);
//			}
//			Collections.reverse(list);
//			map.put("value6", list);
//			
//			//成功打款金额
//			list = new ArrayList<>();
//			for (int i = 0; i < num; i++) {
//				int j = 0;
//				if(i > 0) {
//					j = 0 - i;
//				}
//				String count = StrUtil.null2Str(this.getResult(j, 106));
//				list.add(count.equals("")?"0.0":count);
//			}
//			Collections.reverse(list);
//			map.put("value7", list);
			
			JSONObject json = JSONObject.fromObject(map);
			return json;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * @param day 0-6
	 * @param type 101=当日新增用户数, 102=全部邀请奖励, 103=全部阅读奖励, 104=全部其他奖励, 
	 * 105=申请提现金额, 106=成功打款金额，107=用户总金币，108=用户当日金币，109=预计总支出金额，110=实际已支出总金额
	 * @return
	 */
	private String getResult(int day, int type) {
		Map<String, Object> params = new HashMap<>();
		params.put("currentTime", DateUtil2.getCurrentDateByNum(day));
		//格式化，保留三位小数，四舍五入
		DecimalFormat dFormat = new DecimalFormat("#0.000");
		SysExpenseReportInfoVo sysExpenseReportInfoVo = new SysExpenseReportInfoVo();
		String result = "";
		switch (type) {
//			case 101:
//				result = this.sysExpenseReportInfoService.selectNewUserOfDay(params).getValue();
//				return StrUtil.null2Str(result).equals("")?"0":result;
//			case 102:
//				result = this.sysExpenseReportInfoService.selectAllInviteAwardOfDay(params).getValue();
//				return StrUtil.null2Str(result).equals("")?"0":result;	
//			case 103:
//				result = this.sysExpenseReportInfoService.selectAllReadAwardOfDay(params).getValue();
//				return StrUtil.null2Str(result).equals("")?"0":result;	
//			case 104:
//				result = this.sysExpenseReportInfoService.selectAllOtherAwardOfDay(params).getValue();
//				return StrUtil.null2Str(result).equals("")?"0":result;	
//			case 105:
//				sysExpenseReportInfoVo = this.sysExpenseReportInfoService.selectUserWithdrawOfDay(params);
//				if(!StrUtil.null2Str(sysExpenseReportInfoVo.getValue()).equals("")) {
//					sysExpenseReportInfoVo.setValue(dFormat.format(Double.valueOf(sysExpenseReportInfoVo.getValue())));
//				}else {
//					sysExpenseReportInfoVo.setValue("0.0");
//				}
//				return sysExpenseReportInfoVo.getValue();
//			case 106:
//				sysExpenseReportInfoVo = this.sysExpenseReportInfoService.selectSuccessWithdrawOfDay(params);
//				if(!StrUtil.null2Str(sysExpenseReportInfoVo.getValue()).equals("")) {
//					sysExpenseReportInfoVo.setValue(dFormat.format(Double.valueOf(sysExpenseReportInfoVo.getValue())));
//				}else {
//					sysExpenseReportInfoVo.setValue("0.0");
//				}
//				return sysExpenseReportInfoVo.getValue();
//			case 107:
//				result = this.sysExpenseReportInfoService.selectAllGold().getValue();
//				return StrUtil.null2Str(result).equals("")?"0":result;	
//			case 108:
//				result = this.sysExpenseReportInfoService.selectUserGoldOfDay(params).getValue();
//				return StrUtil.null2Str(result).equals("")?"0":result;	
//			case 109:
//				result = this.sysExpenseReportInfoService.selectAllUserWithdraw(params).getValue();
//				return StrUtil.null2Str(result).equals("")?"0":result;	
//			case 110:
//				result = this.sysExpenseReportInfoService.selectAllSuccessWithdraw(params).getValue();
//				return StrUtil.null2Str(result).equals("")?"0":result;	
			default:
				throw new RuntimeException("错误类型，请检查。day=" + day + ", type=" + type);
		}
	}
	
	public class HttSysReportInfoList implements Serializable{
		private static final long serialVersionUID = -79583709832163829L;
		
		private String dateStr;
		private String newUserOfDay;
		private String allInviteAwardOfDay;
		private String allReadAwardOfDay;
		private String allOtherAwardOfDay;
		private String allAwardOfDay;
		private String userWithdrawOfDay;
		private String successWithdrawOfDay;
		
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
		public String getAllInviteAwardOfDay() {
			return allInviteAwardOfDay;
		}
		public void setAllInviteAwardOfDay(String allInviteAwardOfDay) {
			this.allInviteAwardOfDay = allInviteAwardOfDay;
		}
		public String getAllReadAwardOfDay() {
			return allReadAwardOfDay;
		}
		public void setAllReadAwardOfDay(String allReadAwardOfDay) {
			this.allReadAwardOfDay = allReadAwardOfDay;
		}
		public String getAllOtherAwardOfDay() {
			return allOtherAwardOfDay;
		}
		public void setAllOtherAwardOfDay(String allOtherAwardOfDay) {
			this.allOtherAwardOfDay = allOtherAwardOfDay;
		}
		public String getAllAwardOfDay() {
			return allAwardOfDay;
		}
		public void setAllAwardOfDay(String allAwardOfDay) {
			this.allAwardOfDay = allAwardOfDay;
		}
		public String getUserWithdrawOfDay() {
			return userWithdrawOfDay;
		}
		public void setUserWithdrawOfDay(String userWithdrawOfDay) {
			this.userWithdrawOfDay = userWithdrawOfDay;
		}
		public String getSuccessWithdrawOfDay() {
			return successWithdrawOfDay;
		}
		public void setSuccessWithdrawOfDay(String successWithdrawOfDay) {
			this.successWithdrawOfDay = successWithdrawOfDay;
		}
	}
}
