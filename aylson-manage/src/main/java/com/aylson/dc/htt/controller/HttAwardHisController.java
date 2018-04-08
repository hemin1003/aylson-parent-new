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
import com.aylson.dc.htt.search.HttAwardHisSearch;
import com.aylson.dc.htt.service.HttAwardHisService;
import com.aylson.dc.htt.vo.HttAwardHisVo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;

/**
 * 金币奖励记录
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/awardHis")
public class HttAwardHisController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(HttAwardHisController.class);

	@Autowired
	private HttAwardHisService awardHisService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/awardHis/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttAwardHisSearch awardHisSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			awardHisSearch.setIsPage(true);
			if(!StrUtil.null2Str(awardHisSearch.getCreateDate()).equals("")) {
				awardHisSearch.setTomorrowDate(DateUtil2.formatDate(DateUtils.
						addDays(DateUtil2.parseDate(awardHisSearch.getCreateDate()), 1)));
			}
			List<HttAwardHisVo> list = this.awardHisService.getList(awardHisSearch);
			result.setTotal(this.awardHisService.getRowCount(awardHisSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}
