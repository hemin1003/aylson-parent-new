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
import com.aylson.dc.htt.po.ExpenseInfo;
import com.aylson.dc.htt.search.ExpenseInfoSearch;
import com.aylson.dc.htt.service.ExpenseInfoService;
import com.aylson.dc.htt.service.SysExpenseReportInfoService;
import com.aylson.dc.htt.vo.ExpenseInfoVo;
import com.aylson.dc.htt.vo.SysExpenseReportInfoVo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;

import net.sf.json.JSONObject;

/**
 * 支出统计报表数据查询-新版
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/expenseReportInfo")
public class ExpenseInfoController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(ExpenseInfoController.class);
	
	@Autowired
	private ExpenseInfoService expenseInfoService;
	@Autowired
	private SysExpenseReportInfoService sysExpenseReportInfoService;

	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/ExpenseInfo/index";
	}
	
	/**
	 * 获取详细列表数据
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(ExpenseInfoSearch expenseInfoSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			expenseInfoSearch.setIsPage(true);
			List<Object> list = new ArrayList<>();
			Map<String, Object> params = new HashMap<>();
			params.put("currentTime", expenseInfoSearch.getCurrentTime());
			params.put("tomorrowTime", DateUtil2.getCurrentDateByNum(1));
			for (int i = 0; i < expenseInfoSearch.getNum(); i++) {
				int j = 0;
				if(i > 0) {
					j = 0 - i;
				}
//				if(j == 0) {
//					ExpenseInfoList expenseInfo = new ExpenseInfoList();
//					expenseInfo.setDateStr(DateUtil2.getCurrentDate());
//					expenseInfo.setNewUser(this.getResult(j, 101, 0));
//					expenseInfo.setAllInviteAward(this.getResult(j, 102, 0));
//					expenseInfo.setAllReadAward(this.getResult(j, 103, 0));
//					expenseInfo.setAllOtherAward(this.getResult(j, 104, 0));
//					expenseInfo.setAllAward(this.getResult(j, 108, 0));
//					expenseInfo.setUserWithdrawOfAlipay(this.getResult(j, 105, 2));
//					expenseInfo.setSuccessWithdrawOfAlipay(this.getResult(j, 106, 2));
//					expenseInfo.setUserWithdrawOfWechat(this.getResult(j, 105, 3));
//					expenseInfo.setSuccessWithdrawOfWechat(this.getResult(j, 106, 3));
//					list.add(expenseInfo);
//				}else {}

				ExpenseInfoList expenseInfo = new ExpenseInfoList();
				expenseInfo.setDateStr(DateUtil2.getCurrentDateByNum(j));
				expenseInfoSearch.setCurrentTime(expenseInfo.getDateStr());
				List<ExpenseInfoVo> expenseInfoList = this.expenseInfoService.getList(expenseInfoSearch);
				for (ExpenseInfoVo expenseInfoVo : expenseInfoList) {
					if(expenseInfoVo.getScolumn().equals("allAward")){
						expenseInfo.setAllAward(String.valueOf(expenseInfoVo.getNums()));
						
					}else if(expenseInfoVo.getScolumn().equals("allInviteAward")) {
						expenseInfo.setAllInviteAward(String.valueOf(expenseInfoVo.getNums()));
						
					}else if(expenseInfoVo.getScolumn().equals("allOtherAward")) {
						expenseInfo.setAllOtherAward(String.valueOf(expenseInfoVo.getNums()));
						
					}else if(expenseInfoVo.getScolumn().equals("allReadAward")) {
						expenseInfo.setAllReadAward(String.valueOf(expenseInfoVo.getNums()));
						
					}else if(expenseInfoVo.getScolumn().equals("newUser")) {
						expenseInfo.setNewUser(String.valueOf(expenseInfoVo.getNums()));
						
					}else if(expenseInfoVo.getScolumn().equals("successWithdrawOfAlipay")) {
						expenseInfo.setSuccessWithdrawOfAlipay(String.valueOf(expenseInfoVo.getNums()));
						
					}else if(expenseInfoVo.getScolumn().equals("successWithdrawOfWechat")) {
						expenseInfo.setSuccessWithdrawOfWechat(String.valueOf(expenseInfoVo.getNums()));
						
					}else if(expenseInfoVo.getScolumn().equals("userWithdrawOfAlipay")) {
						expenseInfo.setUserWithdrawOfAlipay(String.valueOf(expenseInfoVo.getNums()));
						
					}else if(expenseInfoVo.getScolumn().equals("userWithdrawOfWechat")) {
						expenseInfo.setUserWithdrawOfWechat(String.valueOf(expenseInfoVo.getNums()));
					}
				}
				list.add(expenseInfo);
			
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
			//今日用户金币
			map.put("todayGold", this.getResult(0, 108, 0));
			//昨日用户金币
			map.put("yesterdayGold", this.getResult(-1, 108, 0));
			//用户总金币
			map.put("allGold", this.getResult(0, 107, 0));
			//预计总支出金额
			map.put("preSuccessWithdraw", this.getResult(0, 109, 0));
			//实际已支出总金额
			map.put("allSuccessWithdraw", this.getResult(0, 110, 0));
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
	private String getResult(int day, int type, int withdrawType) {
		Map<String, Object> params = new HashMap<>();
		params.put("currentTime", DateUtil2.getCurrentDateByNum(day));
		params.put("tomorrowTime", DateUtil2.getCurrentDateByNum(day+1));
		params.put("withdrawType", withdrawType);
		//格式化，保留三位小数，四舍五入
		DecimalFormat dFormat = new DecimalFormat("#0");
		SysExpenseReportInfoVo sysExpenseReportInfoVo = new SysExpenseReportInfoVo();
		String result = "";
		switch (type) {
			case 101:
				result = this.sysExpenseReportInfoService.selectNewUserOfDay(params).getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;
			case 102:
				result = this.sysExpenseReportInfoService.selectAllInviteAwardOfDay(params).getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;	
			case 103:
				result = this.sysExpenseReportInfoService.selectAllReadAwardOfDay(params).getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;	
			case 104:
				result = this.sysExpenseReportInfoService.selectAllOtherAwardOfDay(params).getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;	
			case 105:
				sysExpenseReportInfoVo = this.sysExpenseReportInfoService.selectUserWithdrawOfDay(params);
				if(!StrUtil.null2Str(sysExpenseReportInfoVo.getValue()).equals("")) {
					sysExpenseReportInfoVo.setValue(dFormat.format(Double.valueOf(sysExpenseReportInfoVo.getValue())));
				}else {
					sysExpenseReportInfoVo.setValue("0");
				}
				return sysExpenseReportInfoVo.getValue();
			case 106:
				sysExpenseReportInfoVo = this.sysExpenseReportInfoService.selectSuccessWithdrawOfDay(params);
				if(!StrUtil.null2Str(sysExpenseReportInfoVo.getValue()).equals("")) {
					sysExpenseReportInfoVo.setValue(dFormat.format(Double.valueOf(sysExpenseReportInfoVo.getValue())));
				}else {
					sysExpenseReportInfoVo.setValue("0");
				}
				return sysExpenseReportInfoVo.getValue();
			case 107:
				result = this.sysExpenseReportInfoService.selectAllGold().getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;	
			case 108:
				result = this.sysExpenseReportInfoService.selectUserGoldOfDay(params).getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;	
			case 109:
				result = this.sysExpenseReportInfoService.selectAllUserWithdraw(params).getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;	
			case 110:
				result = this.sysExpenseReportInfoService.selectAllSuccessWithdraw(params).getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;	
			default:
				throw new RuntimeException("错误类型，请检查。day=" + day + ", type=" + type);
		}
	}
	
	public static String[] tagList = new String[] { "allAward", "allInviteAward", "allOtherAward", "allReadAward",
			"newUser", "successWithdrawOfAlipay", "successWithdrawOfWechat", "userWithdrawOfAlipay", "userWithdrawOfWechat"};
	
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
	private List<Object> graphData(String scolumn, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("scolumn", scolumn);
		params.put("limit", limit);
		List<ExpenseInfo> newsHotList = this.expenseInfoService.getNumsOfTag(params);
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < limit; i++) {
			int j = 0;
			if(i > 0) {
				j = 0 - i;
			}
			
			String currentTime = DateUtil2.getCurrentDateByNum(j);
			boolean flag = false;
			for (ExpenseInfo expenseInfo : newsHotList) {
				if(currentTime.equals(DateUtil2.getCurrentDate())) {
					if(expenseInfo.getScolumn().equals("allAward")){
						list.add(this.getResult(j, 108, 0));
						
					}else if(expenseInfo.getScolumn().equals("allInviteAward")) {
						list.add(this.getResult(j, 102, 0));
						
					}else if(expenseInfo.getScolumn().equals("allOtherAward")) {
						list.add(this.getResult(j, 104, 0));
						
					}else if(expenseInfo.getScolumn().equals("allReadAward")) {
						list.add(this.getResult(j, 103, 0));
						
					}else if(expenseInfo.getScolumn().equals("newUser")) {
						list.add(this.getResult(j, 101, 0));
						
					}else if(expenseInfo.getScolumn().equals("successWithdrawOfAlipay")) {
						list.add(this.getResult(j, 106, 2));
						
					}else if(expenseInfo.getScolumn().equals("userWithdrawOfAlipay")) {
						list.add(this.getResult(j, 105, 2));
						
					}else if(expenseInfo.getScolumn().equals("successWithdrawOfWechat")) {
						list.add(this.getResult(j, 106, 3));
						
					}else if(expenseInfo.getScolumn().equals("userWithdrawOfWechat")) {
						list.add(this.getResult(j, 105, 3));
						
					}else {
						list.add("0");
					}
					flag = true;
					break;
				}else {
					if(expenseInfo.getDate().equals(currentTime)) {
						String count = StrUtil.null2Str(expenseInfo.getNums());
						list.add(count.equals("")?"0":count);
						flag = true;
						break;
					}
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
	
	public class ExpenseInfoList implements Serializable{
		
		private static final long serialVersionUID = -79583709832163829L;
		
		private String dateStr;
		private String allAward;
		private String allInviteAward;
		private String allOtherAward;
		private String allReadAward;
		private String newUser;
		private String successWithdrawOfAlipay;
		private String successWithdrawOfWechat;
		private String userWithdrawOfAlipay;
		private String userWithdrawOfWechat;

		public String getDateStr() {
			return dateStr;
		}
		public void setDateStr(String dateStr) {
			this.dateStr = dateStr;
		}
		public String getAllAward() {
			return allAward;
		}
		public void setAllAward(String allAward) {
			this.allAward = allAward;
		}
		public String getAllInviteAward() {
			return allInviteAward;
		}
		public void setAllInviteAward(String allInviteAward) {
			this.allInviteAward = allInviteAward;
		}
		public String getAllOtherAward() {
			return allOtherAward;
		}
		public void setAllOtherAward(String allOtherAward) {
			this.allOtherAward = allOtherAward;
		}
		public String getAllReadAward() {
			return allReadAward;
		}
		public void setAllReadAward(String allReadAward) {
			this.allReadAward = allReadAward;
		}
		public String getNewUser() {
			return newUser;
		}
		public void setNewUser(String newUser) {
			this.newUser = newUser;
		}
		public String getSuccessWithdrawOfAlipay() {
			return successWithdrawOfAlipay;
		}
		public void setSuccessWithdrawOfAlipay(String successWithdrawOfAlipay) {
			this.successWithdrawOfAlipay = successWithdrawOfAlipay;
		}
		public String getSuccessWithdrawOfWechat() {
			return successWithdrawOfWechat;
		}
		public void setSuccessWithdrawOfWechat(String successWithdrawOfWechat) {
			this.successWithdrawOfWechat = successWithdrawOfWechat;
		}
		public String getUserWithdrawOfAlipay() {
			return userWithdrawOfAlipay;
		}
		public void setUserWithdrawOfAlipay(String userWithdrawOfAlipay) {
			this.userWithdrawOfAlipay = userWithdrawOfAlipay;
		}
		public String getUserWithdrawOfWechat() {
			return userWithdrawOfWechat;
		}
		public void setUserWithdrawOfWechat(String userWithdrawOfWechat) {
			this.userWithdrawOfWechat = userWithdrawOfWechat;
		}
	}
}