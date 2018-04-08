package com.aylson.dc.htt.controller;

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
import com.aylson.dc.htt.search.HttBlacklistHisSearch;
import com.aylson.dc.htt.search.HttWithdrawHisSearch;
import com.aylson.dc.htt.search.HttWithdrawProveHisSearch;
import com.aylson.dc.htt.search.HttWithdrawProveUserLinkSearch;
import com.aylson.dc.htt.service.HttBlacklistHisService;
import com.aylson.dc.htt.service.HttWithdrawHisService;
import com.aylson.dc.htt.service.HttWithdrawProveHisService;
import com.aylson.dc.htt.service.HttWithdrawProveUserLinkService;
import com.aylson.dc.htt.vo.HttBlacklistHisVo;
import com.aylson.dc.htt.vo.HttWithdrawHisVo;
import com.aylson.dc.htt.vo.HttWithdrawProveHisVo;
import com.aylson.dc.htt.vo.HttWithdrawProveUserLinkVo;
import com.aylson.dc.sys.common.SessionInfo;
import com.aylson.utils.StrUtil;

import net.sf.json.JSONObject;

/**
 * 用户提现审核管理
 * 
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httWithdrawHisProve")
public class HttWithdrawHisProveController extends BaseController {

	protected static final Logger logger = Logger.getLogger(HttWithdrawHisProveController.class);

	@Autowired
	private HttWithdrawProveHisService httWithdrawProveHisService;
	@Autowired
	private HttWithdrawHisService httWithdrawHisService;
	@Autowired
	private HttWithdrawProveUserLinkService httWithdrawProveUserLinkService;
	@Autowired
	private HttBlacklistHisService httBlacklistHisService;

	private List<String> idList = new ArrayList<>();

	/**
	 * 后台-首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex")
	public String toIndex() {
		return "/jsp/htt/admin/httWithdrawHisProve/index";
	}

	@RequestMapping(value = "/admin/listInfo", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject listInfo(String startDate, String endDate, String isSuspect) {
		try {
			idList.clear();
			HttWithdrawProveHisSearch httWithdrawProveHisSearch = new HttWithdrawProveHisSearch();
			if (isSuspect.equals("1")) {
				httWithdrawProveHisSearch.setAccountStatus(1);
			}
			httWithdrawProveHisSearch.setIsPage(true);
			httWithdrawProveHisSearch.setRows(50000);
			httWithdrawProveHisSearch.setStartDate(startDate + " 00:00:00");
			httWithdrawProveHisSearch.setEndDate(endDate + " 23:59:59");
			idList = this.httWithdrawProveHisService.getIdList(httWithdrawProveHisSearch);
			if (idList.size() > 0) {
				HttWithdrawProveHisVo httWithdrawProveHisOne = httWithdrawProveHisService.getById(idList.get(0));
				httWithdrawProveHisOne.setTotalPage(String.valueOf(idList.size()));
				httWithdrawProveHisOne.setCurPage("0");
				JSONObject json = JSONObject.fromObject(httWithdrawProveHisOne);
				return json;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@RequestMapping(value = "/admin/listNext", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject listNext(String index) {
		try {
			HttWithdrawProveHisSearch httWithdrawProveHisSearch = new HttWithdrawProveHisSearch();
			httWithdrawProveHisSearch.setIsPage(true);
			HttWithdrawProveHisVo httWithdrawProveHisVo = this.httWithdrawProveHisService
					.getById(idList.get(Integer.parseInt(index)));
			JSONObject json = JSONObject.fromObject(httWithdrawProveHisVo);
			return json;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 获取列表
	 * 
	 * @return list1
	 */
	@RequestMapping(value = "/admin/listTop")
	@ResponseBody
	public EasyuiDataGridJson listTop(HttWithdrawHisSearch httWithdrawHisSearch) {
		EasyuiDataGridJson result = new EasyuiDataGridJson();// 页面DataGrid结果集
		try {
			httWithdrawHisSearch.setIsPage(true);
			List<HttWithdrawHisVo> list = this.httWithdrawHisService.getList(httWithdrawHisSearch);
			result.setTotal(this.httWithdrawHisService.getRowCount(httWithdrawHisSearch));
			result.setRows(list);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 获取列表
	 * 
	 * @return list2
	 */
	@RequestMapping(value = "/admin/listBottom")
	@ResponseBody
	public EasyuiDataGridJson listBottom(HttWithdrawProveUserLinkSearch httWithdrawProveUserLinkSearch) {
		EasyuiDataGridJson result = new EasyuiDataGridJson();// 页面DataGrid结果集
		try {
			httWithdrawProveUserLinkSearch.setIsPage(true);
			List<HttWithdrawProveUserLinkVo> list = this.httWithdrawProveUserLinkService
					.getList(httWithdrawProveUserLinkSearch);
			result.setTotal(this.httWithdrawProveUserLinkService.getRowCount(httWithdrawProveUserLinkSearch));
			result.setRows(list);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 后台-查看详情页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/toEdit", method = RequestMethod.GET)
	public String toEdit(String id) {
		if (id != null) {
			HttWithdrawProveUserLinkVo httWithdrawProveUserLinkVo = this.httWithdrawProveUserLinkService.getById(id);
			if (!StrUtil.null2Str(httWithdrawProveUserLinkVo.getInstalledList()).equals("")) {
				httWithdrawProveUserLinkVo
						.setInstalledList(httWithdrawProveUserLinkVo.getInstalledList().replaceAll("\"", ""));
			}
			this.request.setAttribute("httWithdrawProveUserLinkVo", httWithdrawProveUserLinkVo);
		}
		return "/jsp/htt/admin/httWithdrawHisProve/add";
	}

	/**
	 * 获取列表
	 * 
	 * @return list2
	 */
	@RequestMapping(value = "/admin/listBlackListHis")
	@ResponseBody
	public EasyuiDataGridJson listBlackListHis(HttBlacklistHisSearch httBlacklistHisSearch) {
		EasyuiDataGridJson result = new EasyuiDataGridJson();// 页面DataGrid结果集
		try {
			httBlacklistHisSearch.setIsPage(true);
			List<HttBlacklistHisVo> list = this.httBlacklistHisService.getList(httBlacklistHisSearch);
			result.setTotal(this.httBlacklistHisService.getRowCount(httBlacklistHisSearch));
			result.setRows(list);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 后台-更新用户提现状态
	 * 
	 * @param noticeConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/updateWithdrawStatus", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject updateWithdrawStatus(String phoneNum, String status, String id) {
		try {
			// 获取当前httWithdrawHisVo
			HttWithdrawHisSearch httWithdrawHisSearch = new HttWithdrawHisSearch();
			httWithdrawHisSearch.setIsPage(true);
			httWithdrawHisSearch.setPhoneNum(phoneNum);
			httWithdrawHisSearch.setStatusType(1);	//取处理中的记录
			httWithdrawHisSearch.setRows(1000);
			List<HttWithdrawHisVo> httWithdrawHisVoList = this.httWithdrawHisService.getList(httWithdrawHisSearch);
			SessionInfo sessionInfo = (SessionInfo) this.request.getSession().getAttribute("sessionInfo");
			if (httWithdrawHisVoList != null && httWithdrawHisVoList.size() > 0) {
				logger.info("phoneNum=" + phoneNum + "，"
						+ "待更新的提现记录条数（处理中）size=" + httWithdrawHisVoList.size());
				return this.httWithdrawProveHisService.updateHttWithdrawProveHisList(httWithdrawHisVoList, phoneNum, status,
						sessionInfo, id);
				
			}
		} catch (Exception e) {
			logger.error("审核记录异常：" + e.getMessage(), e);
			Map<String, Object> map = new HashMap<>();
			map.put("result", e.getMessage());
			map.put("success", "3");
			JSONObject json = JSONObject.fromObject(map);
			return json;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("result", "无数据处理");
		map.put("success", "4");
		return JSONObject.fromObject(map);
	}
}