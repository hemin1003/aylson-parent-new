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
import com.aylson.dc.ytt.search.SpiderEmailSearch;
import com.aylson.dc.ytt.service.SpiderEmailService;
import com.aylson.dc.ytt.vo.SpiderEmailVo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.UUIDUtils;

/**
 * 爬虫邮件告警配置
 * @author Minbo
 */
@Controller
@RequestMapping("/ytt/spiderEmail")
public class SpiderEmailController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(SpiderEmailController.class);

	@Autowired
	private SpiderEmailService spiderEmailService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/ytt/admin/spiderEmail/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(SpiderEmailSearch spiderEmailSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			spiderEmailSearch.setIsPage(true);
			List<SpiderEmailVo> list = this.spiderEmailService.getList(spiderEmailSearch);
			result.setTotal(this.spiderEmailService.getRowCount(spiderEmailSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 后台-添加页面
	 * @param spiderEmailVo
	 * @return
	 */
	@RequestMapping(value = "/admin/toAdd", method = RequestMethod.GET)
	public String toAdd(SpiderEmailVo spiderEmailVo) {
		this.request.setAttribute("spiderEmailVo", spiderEmailVo);
		return "/jsp/ytt/admin/spiderEmail/add";
	}
	
	/**
	 * 后台-添加保存
	 * @param spiderEmailVo
	 * @return
	 */
	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(SpiderEmailVo spiderEmailVo) {
		Result result = new Result();
		try{
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			spiderEmailVo.setId(UUIDUtils.create());
			String cTime = DateUtil2.getCurrentLongDateTime();
			spiderEmailVo.setCreatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			spiderEmailVo.setCreateDate(cTime);
			spiderEmailVo.setUpdateDate(cTime);
			Boolean flag = this.spiderEmailService.add(spiderEmailVo);
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
			SpiderEmailVo spiderEmailVo = this.spiderEmailService.getById(id);
			this.request.setAttribute("spiderEmailVo", spiderEmailVo);
		}
		return "/jsp/ytt/admin/spiderEmail/add";
	}
	
	/**
	 * 后台-编辑保存
	 * @param spiderEmailVo
	 * @return
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(SpiderEmailVo spiderEmailVo) {
		Result result = new Result();
		try {
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			spiderEmailVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			spiderEmailVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.spiderEmailService.edit(spiderEmailVo);
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
			Boolean flag = this.spiderEmailService.deleteById(id);
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
