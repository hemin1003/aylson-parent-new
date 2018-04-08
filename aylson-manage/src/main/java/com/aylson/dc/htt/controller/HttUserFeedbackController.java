package com.aylson.dc.htt.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aylson.core.easyui.EasyuiDataGridJson;
import com.aylson.core.frame.controller.BaseController;
import com.aylson.dc.htt.search.HttUserFeedbackSearch;
import com.aylson.dc.htt.service.HttUserFeedbackService;
import com.aylson.dc.htt.vo.HttUserFeedbackVo;

/**
 * 用户反馈信息
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httUserFeedback")
public class HttUserFeedbackController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(HttUserFeedbackController.class);

	@Autowired
	private HttUserFeedbackService httUserFeedbackService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/httUserFeedback/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttUserFeedbackSearch httUserFeedbackSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			httUserFeedbackSearch.setIsPage(true);
			List<HttUserFeedbackVo> list = this.httUserFeedbackService.getList(httUserFeedbackSearch);
			result.setTotal(this.httUserFeedbackService.getRowCount(httUserFeedbackSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}
