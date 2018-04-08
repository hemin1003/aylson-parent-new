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
import com.aylson.dc.htt.po.HttVideoInfoReport;
import com.aylson.dc.htt.search.HttVideoInfoReportSearch;
import com.aylson.dc.htt.service.HttVideoInfoReportService;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;

/**
 * 阅读统计报表
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httVideoInfoReport")
public class HttVideoInfoReportController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(HttVideoInfoReportController.class);

	@Autowired
	private HttVideoInfoReportService videoInfoReportService ;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/httVideoInfoReport/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttVideoInfoReportSearch videoInfoReportSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			
			String cTime = DateUtil2.getCurrentLongDateTime();
			videoInfoReportSearch.setIsDateSearch(1);
			if(StrUtil.null2Str(videoInfoReportSearch.getDate()).equals("")) {
				videoInfoReportSearch.setIsDateSearch(0);
				videoInfoReportSearch.setDate(DateUtil2.formatDate(DateUtils.
						addDays(DateUtil2.parseDate(cTime), -1)));
				videoInfoReportSearch.setSevenDayAgo(DateUtil2.formatDate(DateUtils.
						addDays(DateUtil2.parseDate(cTime), -7)));
			}
			videoInfoReportSearch.setIsPage(true);
			List<HttVideoInfoReport> list = this.videoInfoReportService.getList(videoInfoReportSearch);
			result.setTotal(this.videoInfoReportService.getRowCount(videoInfoReportSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
}
