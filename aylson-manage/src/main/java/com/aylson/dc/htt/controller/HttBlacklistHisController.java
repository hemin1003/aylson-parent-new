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
import com.aylson.dc.htt.search.HttBlacklistHisSearch;
import com.aylson.dc.htt.service.HttBlacklistHisService;
import com.aylson.dc.htt.vo.HttBlacklistHisVo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;

/**
 * 命中黑名单规则历史管理
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httBlacklistHis")
public class HttBlacklistHisController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(HttBlacklistHisController.class);

	@Autowired
	private HttBlacklistHisService blacklistHisService ;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/httBlacklistHis/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttBlacklistHisSearch blacklistHisSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			blacklistHisSearch.setIsPage(true);
			if(!StrUtil.null2Str(blacklistHisSearch.getCreateDate()).equals("")) {
				blacklistHisSearch.setTomorrowDate(DateUtil2.formatDate(DateUtils.
						addDays(DateUtil2.parseDate(blacklistHisSearch.getCreateDate()), 1)));
			}
			List<HttBlacklistHisVo> list = this.blacklistHisService.getList(blacklistHisSearch);
			result.setTotal(this.blacklistHisService.getRowCount(blacklistHisSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
}
