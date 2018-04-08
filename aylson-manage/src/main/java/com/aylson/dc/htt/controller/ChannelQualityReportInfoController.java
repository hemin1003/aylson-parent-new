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
import com.aylson.dc.htt.search.ChannelQualityReportInfoSearch;
import com.aylson.dc.htt.service.ChannelQualityReportInfoService;
import com.aylson.dc.htt.vo.ChannelQualityReportInfoVo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;

/**
 * 分渠道质量数据统计
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/channelQualityReportInfo")
public class ChannelQualityReportInfoController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(ChannelQualityReportInfoController.class);

	@Autowired
	private ChannelQualityReportInfoService channelQualityReportInfoService ;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/channelQualityReportInfo/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(ChannelQualityReportInfoSearch channelQualityReportInfoSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			String cTime = DateUtil2.getCurrentLongDateTime();
			channelQualityReportInfoSearch.setIsDateSearch(1);
			if(StrUtil.null2Str(channelQualityReportInfoSearch.getDate()).equals("")) {
				channelQualityReportInfoSearch.setIsDateSearch(0);
				channelQualityReportInfoSearch.setDate(DateUtil2.formatDate(DateUtils.
						addDays(DateUtil2.parseDate(cTime), -1)));
				channelQualityReportInfoSearch.setSevenDayAgo(DateUtil2.formatDate(DateUtils.
						addDays(DateUtil2.parseDate(cTime), -7)));
			}
			channelQualityReportInfoSearch.setIsPage(true);
			List<ChannelQualityReportInfoVo> list = this.channelQualityReportInfoService.getList(channelQualityReportInfoSearch);
			result.setTotal(this.channelQualityReportInfoService.getRowCount(channelQualityReportInfoSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
}
