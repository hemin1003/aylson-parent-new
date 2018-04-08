package com.aylson.dc.htt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aylson.core.easyui.EasyuiDataGridJson;
import com.aylson.core.frame.controller.BaseController;
import com.aylson.core.frame.domain.Result;
import com.aylson.dc.htt.search.HttAppUserSearch;
import com.aylson.dc.htt.service.HttAppUserService;
import com.aylson.dc.htt.vo.HttAppUserVo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;

/**
 * 用户信息管理
 * 
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httSendSmsToInactiveUser")
public class HttSendSmsToInactiveUserController extends BaseController {

	protected static final Logger logger = Logger.getLogger(HttSendSmsToInactiveUserController.class);

	@Autowired
	private HttAppUserService appUserService;
	
	private List<String> phoneNumList = new ArrayList<String>();

	/**
	 * 后台-首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/httSendSmsToInactiveUser/index";
	}

	/**
	 * 获取列表
	 * 
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttAppUserSearch appUserSearch) {
		EasyuiDataGridJson result = new EasyuiDataGridJson();// 页面DataGrid结果集
		try {
			if(!StrUtil.null2Str(appUserSearch.getRealPhoneNum()).equals("") ||
					!StrUtil.null2Str(appUserSearch.getStudents()).equals("") ||
					!StrUtil.null2Str(appUserSearch.getUnUseDay()).equals("") ||
					!StrUtil.null2Str(appUserSearch.getGold()).equals("")
					) {
				//设置上限10W
				appUserSearch.setRows(100000);
			}
			phoneNumList.clear();
			appUserSearch.setIsPage(true);
			appUserSearch.setIsBindRealPhone(1);
			String cTime = DateUtil2.getCurrentLongDateTime();
			if (!StrUtil.null2Str(appUserSearch.getUnUseDay()).equals("")) {
				System.out.println("appUserSearch" + appUserSearch.getUnUseDay());
				appUserSearch.setUnUseDate(DateUtil2.formatDate(
						DateUtils.addDays(DateUtil2.parseDate(cTime), -Integer.parseInt(appUserSearch.getUnUseDay()))));
			}
			List<HttAppUserVo> list = this.appUserService.getList(appUserSearch);
			for (HttAppUserVo httAppUserVo : list) {
				phoneNumList.add(httAppUserVo.getRealPhoneNum());
				}
			result.setTotal(this.appUserService.getRowCount(appUserSearch));
			result.setRows(list);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 发送短信
	 * 
	 * @return list
	 */
	@RequestMapping(value = "/admin/sendSms", method = RequestMethod.POST)
	@ResponseBody
	public Result sendSms(HttpServletRequest req) {
		try {
			// 去掉最后一个空格
	        	Map<String, String[]> params = request.getParameterMap();
	        	List<String> list = new ArrayList<>();
	        	if(params.size() > 0) {
	    	        for (String key : params.keySet()) {
	    	            String[] values = params.get(key);  
	    	            for (int i = 0; i < values.length; i++) {  
	    	                String value = values[i];  
	    	                list.add(value);
	    	            }
	    	        }
	    	        return appUserService.sendSms(list);
	        	}else {
	        		return appUserService.sendSms(phoneNumList);
	        	}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

}