package com.aylson.dc.htt.controller;

import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aylson.core.easyui.EasyuiDataGridJson;
import com.aylson.core.frame.controller.BaseController;
import com.aylson.dc.htt.search.HttDakaReportSearch;
import com.aylson.dc.htt.service.HttDakaReportService;
import com.aylson.dc.htt.vo.HttDakaReportVo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;

/**
 * 大咖统计查询
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httDakaReport")
public class HttDakaReportController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(HttDakaReportController.class);

	@Autowired
	private HttDakaReportService httDakaReportService ;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/httDakaReport/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttDakaReportSearch httDakaReportSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			String cTime = DateUtil2.getCurrentLongDateTime();
			httDakaReportSearch.setIsDateSearch(1);
			if(StrUtil.null2Str(httDakaReportSearch.getDate()).equals("")) {
				httDakaReportSearch.setIsDateSearch(0);
				httDakaReportSearch.setDate(DateUtil2.formatDate(DateUtils.
						addDays(DateUtil2.parseDate(cTime), -1)));
				httDakaReportSearch.setSevenDayAgo(DateUtil2.formatDate(DateUtils.
						addDays(DateUtil2.parseDate(cTime), -7)));
			}
			httDakaReportSearch.setIsPage(true);
			List<HttDakaReportVo> list = this.httDakaReportService.getList(httDakaReportSearch);
			result.setTotal(this.httDakaReportService.getRowCount(httDakaReportSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
}
