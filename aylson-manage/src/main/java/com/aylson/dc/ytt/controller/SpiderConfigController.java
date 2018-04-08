package com.aylson.dc.ytt.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aylson.core.easyui.EasyuiDataGridJson;
import com.aylson.core.frame.controller.BaseController;
import com.aylson.core.frame.domain.Result;
import com.aylson.core.frame.domain.ResultCode;
import com.aylson.dc.sys.common.SessionInfo;
import com.aylson.dc.ytt.search.SpiderConfigSearch;
import com.aylson.dc.ytt.service.SpiderConfigService;
import com.aylson.dc.ytt.vo.SpiderConfigVo;
import com.aylson.utils.DateUtil2;

/**
 * 爬虫信息配置
 * @author Minbo
 */
@Controller
@RequestMapping("/ytt/spiderConfig")
public class SpiderConfigController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(SpiderConfigController.class);

	@Autowired
	private SpiderConfigService spiderConfigService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/ytt/admin/spiderConfig/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(SpiderConfigSearch spiderConfigSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			spiderConfigSearch.setIsPage(true);
			List<SpiderConfigVo> list = this.spiderConfigService.getList(spiderConfigSearch);
			result.setTotal(this.spiderConfigService.getRowCount(spiderConfigSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 后台-编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/toEdit", method = RequestMethod.GET)
	public String toEdit(String id) {
		if(id != null){
			SpiderConfigVo spiderConfigVo = this.spiderConfigService.getById(id);
			this.request.setAttribute("spiderConfigVo", spiderConfigVo);
		}
		return "/jsp/ytt/admin/spiderConfig/add";
	}
	
	/**
	 * 后台-编辑保存
	 * @param spiderConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(SpiderConfigVo spiderConfigVo) {
		Result result = new Result();
		try {
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			spiderConfigVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			spiderConfigVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.spiderConfigService.edit(spiderConfigVo);
			if(flag){
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
			}else{
				result.setError(ResultCode.CODE_STATE_4006, "操作失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
}
