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
import com.aylson.dc.htt.search.HttUserInfoSearch;
import com.aylson.dc.htt.service.HttUserInfoService;
import com.aylson.dc.htt.service.SysUserReportInfoService;
import com.aylson.dc.htt.vo.HttUserInfoVo;
import com.aylson.utils.DateUtil;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;

import net.sf.json.JSONObject;

/**
 * 用户统计报表数据查询-新版
 * 
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httUserReportInfo")
public class HttUserInfoController extends BaseController {

	protected static final Logger logger = Logger.getLogger(HttUserInfoController.class);

	@Autowired
	private HttUserInfoService userInfoService;
	@Autowired
	private SysUserReportInfoService sysUserReportInfoService;

	/**
	 * 后台-首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/sysUserReportInfo/index";
	}

	/**
	 * 获取详细列表数据
	 * 
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttUserInfoSearch httUserInfoSearch) {
		EasyuiDataGridJson result = new EasyuiDataGridJson();// 页面DataGrid结果集
		try {
			httUserInfoSearch.setIsPage(true);
			List<Object> list = new ArrayList<>();
			Map<String, Object> params = new HashMap<>();
			params.put("currentTime", httUserInfoSearch.getCurrentTime());
			params.put("tomorrowTime", DateUtil2.getCurrentDateByNum(1));
			for (int i = 0; i < httUserInfoSearch.getNum(); i++) {
				int j = 0;
				if (i > 0) {
					j = 0 - i;
				}
//				if (j == 0) {
//					UserInfoList userInfo = new UserInfoList();
//					userInfo.setDateStr(DateUtil2.getCurrentDate());
//					userInfo.setAllUser(this.getResult(j, 106));
//					userInfo.setNewUserOfDay(this.getResult(j, 101));
//					userInfo.setInviteUserOfDay(this.getResult(j, 102));
//					userInfo.setInvitePercentOfDay(this.getResult(j, 103));
//					userInfo.setArticleCountOfDay(this.getResult(j, 104));
//					userInfo.setReadCountOfDay(this.getResult(j, 105));
//					list.add(userInfo);
//				} else {}

				UserInfoList userInfo = new UserInfoList();
				userInfo.setDateStr(DateUtil2.getCurrentDateByNum(j));
				httUserInfoSearch.setCurrentTime(userInfo.getDateStr());
				List<HttUserInfoVo> userInfoList = this.userInfoService.getList(httUserInfoSearch);
				
				String newUser = null;
				String inviteUser = null;
				DecimalFormat dFormat = new DecimalFormat("#0");
				for (HttUserInfoVo httUserInfoVo : userInfoList) {
					if(httUserInfoVo.getScolumn().equals("newUserOfDay")) {
						newUser = String.valueOf(httUserInfoVo.getNums());
					}else if(httUserInfoVo.getScolumn().equals("inviteUserOfDay")){
						inviteUser = String.valueOf(httUserInfoVo.getNums());
					}
				}
				
				for (HttUserInfoVo httUserInfoVo : userInfoList) {
					userInfo.setDateStr(httUserInfoVo.getDate());
					if (httUserInfoVo.getScolumn().equals("allUser")) {
						userInfo.setAllUser(String.valueOf(httUserInfoVo.getNums()));

					} else if (httUserInfoVo.getScolumn().equals("newUserOfDay")) {
						userInfo.setNewUserOfDay(String.valueOf(httUserInfoVo.getNums()));

					} else if (httUserInfoVo.getScolumn().equals("inviteUserOfDay")) {
						userInfo.setInviteUserOfDay(String.valueOf(httUserInfoVo.getNums()));

					} else if (httUserInfoVo.getScolumn().equals("invitePercentOfDay")) {
						//userInfo.setInvitePercentOfDay(String.valueOf(httUserInfoVo.getNums()));
						if(newUser.equals("0")) {
							userInfo.setInvitePercentOfDay("0");
						}else {
							double a = (Double.valueOf(inviteUser) / Double.valueOf(newUser)) * 100;
							String invitePercent =(a==0?"0":dFormat.format(a) + "%");
							userInfo.setInvitePercentOfDay(invitePercent);
						}
					} else if (httUserInfoVo.getScolumn().equals("articleCountOfDay")) {
						userInfo.setArticleCountOfDay(String.valueOf(httUserInfoVo.getNums()));

					} else if (httUserInfoVo.getScolumn().equals("readCountOfDay")) {
						userInfo.setReadCountOfDay(String.valueOf(httUserInfoVo.getNums()));
					} 
				}
				list.add(userInfo);
			
			}
			result.setTotal(Long.valueOf(list.size()));
			result.setRows(list);
			return result;
		} catch (Exception e) {
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
	 * @param day
	 *            0-6
	 * @param type
	 *            101=当日新增用户数, 102=自邀请新增, 103=自邀请比例, 104=阅读文章数, 105=文章阅读次数，106=用户总数
	 * @return
	 */
	private String getResult(int day, int type) {
		Map<String, Object> params = new HashMap<>();
		params.put("currentTime", DateUtil.getCurrentDate(day));
		params.put("tomorrowTime", DateUtil.getCurrentDate(day + 1));
		String result = "";
		switch (type) {
		case 101:
			result = this.sysUserReportInfoService.selectNewUserOfDay(params).getValue();
			return StrUtil.null2Str(result).equals("") ? "0" : result;
		case 102:
			result = this.sysUserReportInfoService.selectInviteUserOfDay(params).getValue();
			return StrUtil.null2Str(result).equals("") ? "0" : result;
		case 103:
			return "100";
		case 104:
			result = this.sysUserReportInfoService.selectArticleCountOfDay(params).getValue();
			return StrUtil.null2Str(result).equals("") ? "0" : result;
		case 105:
			result = this.sysUserReportInfoService.selectReadCountOfDay(params).getValue();
			return StrUtil.null2Str(result).equals("") ? "0" : result;
		case 106:
			result = this.sysUserReportInfoService.selectAllUser().getValue();
			return StrUtil.null2Str(result).equals("") ? "0" : result;
		default:
			throw new RuntimeException("错误类型，请检查。day=" + day + ", type=" + type);
		}
	}

//	public static String[] tagList = new String[] { "allAward", "allInviteAward", "allOtherAward", "allReadAward",
//			"newUser", "successWithdrawOfAlipay", "successWithdrawOfWechat", "userWithdrawOfAlipay",
//			"userWithdrawOfWechat" };

	/**
	 * 获取折线图数据
	 * 
	 * @return list
	 */
//	@RequestMapping(value = "/admin/listMap", method = RequestMethod.GET)
//	@ResponseBody
//	public JSONObject listMap(int num) {
//		try {
//			Map<String, Object> map = new HashMap<>();
//			// 横坐标，统计七天的数据，含今天
//			List<Object> list = new ArrayList<>();
//			for (int i = 0; i < num; i++) {
//				int j = 0;
//				if (i > 0) {
//					j = 0 - i;
//				}
//				list.add(DateUtil2.getCurrentDateByNum(j));
//			}
//			Collections.reverse(list);
//			map.put("categories", list);
//			// 各类型tag数据
//			for (int i = 0; i < tagList.length; i++) {
//				String tag = tagList[i];
//				list = new ArrayList<>();
//				list = this.graphData(tag, num);
//				map.put(tag, list);
//			}
//			JSONObject json = JSONObject.fromObject(map);
//			return json;
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return null;
//		}
//	}

	/**
	 * 获取tag类型的数据
	 * 
	 * @param domain
	 * @param limit
	 * @return
	 */
//	private List<Object> graphData(String scolumn, int limit) {
//		Map<String, Object> params = new HashMap<>();
//		params.put("scolumn", scolumn);
//		params.put("limit", limit);
//		List<ExpenseInfo> newsHotList = this.expenseInfoService.getNumsOfTag(params);
//		List<Object> list = new ArrayList<>();
//		for (int i = 0; i < limit; i++) {
//			int j = 0;
//			if (i > 0) {
//				j = 0 - i;
//			}
//
//			String currentTime = DateUtil2.getCurrentDateByNum(j);
//			boolean flag = false;
//			for (ExpenseInfo expenseInfo : newsHotList) {
//				if (currentTime.equals(DateUtil2.getCurrentDate())) {
//					if (expenseInfo.getScolumn().equals("allAward")) {
//						list.add(this.getResult(j, 108, 0));
//
//					} else if (expenseInfo.getScolumn().equals("allInviteAward")) {
//						list.add(this.getResult(j, 102, 0));
//
//					} else if (expenseInfo.getScolumn().equals("allOtherAward")) {
//						list.add(this.getResult(j, 104, 0));
//
//					} else if (expenseInfo.getScolumn().equals("allReadAward")) {
//						list.add(this.getResult(j, 103, 0));
//
//					} else if (expenseInfo.getScolumn().equals("newUser")) {
//						list.add(this.getResult(j, 101, 0));
//
//					} else if (expenseInfo.getScolumn().equals("successWithdrawOfAlipay")) {
//						list.add(this.getResult(j, 106, 2));
//
//					} else if (expenseInfo.getScolumn().equals("userWithdrawOfAlipay")) {
//						list.add(this.getResult(j, 105, 2));
//
//					} else if (expenseInfo.getScolumn().equals("successWithdrawOfWechat")) {
//						list.add(this.getResult(j, 106, 3));
//
//					} else if (expenseInfo.getScolumn().equals("userWithdrawOfWechat")) {
//						list.add(this.getResult(j, 105, 3));
//
//					} else {
//						list.add("0");
//					}
//					flag = true;
//					break;
//				} else {
//					if (expenseInfo.getDate().equals(currentTime)) {
//						String count = StrUtil.null2Str(expenseInfo.getNums());
//						list.add(count.equals("") ? "0" : count);
//						flag = true;
//						break;
//					}
//				}
//			}
//			// 如果没有匹配，则为0
//			if (!flag) {
//				list.add("0");
//			}
//		}
//		Collections.reverse(list);
//		return list;
//	}

	public class UserInfoList implements Serializable {

		private static final long serialVersionUID = -79583709832163829L;

		private String dateStr;
		private String allUser;
		private String newUserOfDay;
		private String inviteUserOfDay;
		private String invitePercentOfDay;
		private String articleCountOfDay;
		private String readCountOfDay;

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

		public String getInvitePercentOfDay() {
			return invitePercentOfDay;
		}

		public void setInvitePercentOfDay(String invitePercentOfDay) {
			this.invitePercentOfDay = invitePercentOfDay;
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

		public String getAllUser() {
			return allUser;
		}

		public void setAllUser(String allUser) {
			this.allUser = allUser;
		}

	}
}