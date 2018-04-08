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
import com.aylson.dc.ytt.search.SpiderSmsSearch;
import com.aylson.dc.ytt.service.SpiderSmsService;
import com.aylson.dc.ytt.vo.SpiderSmsVo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.UUIDUtils;

/**
 * 爬虫短信告警配置
 * @author Minbo
 */
@Controller
@RequestMapping("/ytt/spiderSms")
public class SpiderSmsController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(SpiderSmsController.class);

	@Autowired
	private SpiderSmsService spiderSmsService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/ytt/admin/spiderSms/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(SpiderSmsSearch spiderSmsSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			spiderSmsSearch.setIsPage(true);
			List<SpiderSmsVo> list = this.spiderSmsService.getList(spiderSmsSearch);
			result.setTotal(this.spiderSmsService.getRowCount(spiderSmsSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 后台-添加页面
	 * @param spiderSmsVo
	 * @return
	 */
	@RequestMapping(value = "/admin/toAdd", method = RequestMethod.GET)
	public String toAdd(SpiderSmsVo spiderSmsVo) {
		this.request.setAttribute("spiderSmsVo", spiderSmsVo);
		return "/jsp/ytt/admin/spiderSms/add";
	}
	
	/**
	 * 后台-添加保存
	 * @param spiderSmsVo
	 * @return
	 */
	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(SpiderSmsVo spiderSmsVo) {
		Result result = new Result();
		try{
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			spiderSmsVo.setId(UUIDUtils.create());
			String cTime = DateUtil2.getCurrentLongDateTime();
			spiderSmsVo.setCreatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			spiderSmsVo.setCreateDate(cTime);
			spiderSmsVo.setUpdateDate(cTime);
			Boolean flag = this.spiderSmsService.add(spiderSmsVo);
			if(flag){
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
			}else{
				result.setError(ResultCode.CODE_STATE_4006, "操作失败");
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 后台-编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/toEdit", method = RequestMethod.GET)
	public String toEdit(String id) {
		if(id != null){
			SpiderSmsVo spiderSmsVo = this.spiderSmsService.getById(id);
			this.request.setAttribute("spiderSmsVo", spiderSmsVo);
		}
		return "/jsp/ytt/admin/spiderSms/add";
	}
	
	/**
	 * 后台-编辑保存
	 * @param spiderSmsVo
	 * @return
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(SpiderSmsVo spiderSmsVo) {
		Result result = new Result();
		try {
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			spiderSmsVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			spiderSmsVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.spiderSmsService.edit(spiderSmsVo);
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
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/deleteById", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteById(String id) {
		Result result = new Result();
		try{
			Boolean flag = this.spiderSmsService.deleteById(id);
			if(flag){
				result.setOK(ResultCode.CODE_STATE_200, "删除成功");
			}else{
				result.setError(ResultCode.CODE_STATE_4006, "删除失败");
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
}
