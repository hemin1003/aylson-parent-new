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
import com.aylson.dc.htt.search.HttAwardHisChildSearch;
import com.aylson.dc.htt.service.HttAwardHisChildService;
import com.aylson.dc.htt.vo.HttAwardHisChildVo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;

/**
 * 金币奖励记录
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httAwardHisChild")
public class HttAwardHisChildController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(HttAwardHisChildController.class);

	@Autowired
	private HttAwardHisChildService awardHisChildService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/httAwardHisChild/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttAwardHisChildSearch awardHisChildSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			awardHisChildSearch.setIsPage(true);
			if(!StrUtil.null2Str(awardHisChildSearch.getCreateDate()).equals("")) {
				awardHisChildSearch.setTomorrowDate(DateUtil2.formatDate(DateUtils.
						addDays(DateUtil2.parseDate(awardHisChildSearch.getCreateDate()), 1)));
			}
			List<HttAwardHisChildVo> list = this.awardHisChildService.getList(awardHisChildSearch);
			result.setTotal(this.awardHisChildService.getRowCount(awardHisChildSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}
