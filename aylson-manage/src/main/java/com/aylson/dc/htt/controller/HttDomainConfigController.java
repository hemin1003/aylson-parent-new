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
import com.aylson.core.frame.domain.Result;
import com.aylson.core.frame.domain.ResultCode;
import com.aylson.dc.htt.search.HttDomainConfigSearch;
import com.aylson.dc.htt.service.HttDomainConfigService;
import com.aylson.dc.htt.vo.HttDomainConfigVo;
import com.aylson.dc.sys.common.SessionInfo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.UUIDUtils;

/**
 * 域名配置
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httDomainConfig")
public class HttDomainConfigController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(HttDomainConfigController.class);

	@Autowired
	private HttDomainConfigService httDomainConfigService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/httDomainConfig/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttDomainConfigSearch httDomainConfigSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			httDomainConfigSearch.setIsPage(true);
			List<HttDomainConfigVo> list = this.httDomainConfigService.getList(httDomainConfigSearch);
			result.setTotal(this.httDomainConfigService.getRowCount(httDomainConfigSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 后台-添加页面
	 * @param httDomainConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/toAdd", method = RequestMethod.GET)
	public String toAdd(HttDomainConfigVo httDomainConfigVo) {
		this.request.setAttribute("httDomainConfigVo", httDomainConfigVo);
		return "/jsp/htt/admin/httDomainConfig/add";
	}
	
	/**
	 * 后台-添加保存
	 * @param httDomainConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(HttDomainConfigVo httDomainConfigVo) {
		Result result = new Result();
		try{
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			httDomainConfigVo.setId(UUIDUtils.create());
			httDomainConfigVo.setStatus(1); //默认下线
			String cTime = DateUtil2.getCurrentLongDateTime();
			httDomainConfigVo.setCreatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			httDomainConfigVo.setCreateDate(cTime);
			httDomainConfigVo.setUpdateDate(cTime);
			Boolean flag = this.httDomainConfigService.add(httDomainConfigVo);
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
			HttDomainConfigVo httDomainConfigVo = this.httDomainConfigService.getById(id);
			this.request.setAttribute("httDomainConfigVo", httDomainConfigVo);
		}
		return "/jsp/htt/admin/httDomainConfig/add";
	}
	
	/**
	 * 后台-编辑保存
	 * @param httDomainConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(HttDomainConfigVo httDomainConfigVo) {
		Result result = new Result();
		try {
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			httDomainConfigVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			httDomainConfigVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.httDomainConfigService.edit(httDomainConfigVo);
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
			Boolean flag = this.httDomainConfigService.deleteById(id);
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
	
	@RequestMapping(value = "/admin/changeStatus", method = RequestMethod.POST)
	@ResponseBody
	public Result changeStatus(HttDomainConfigVo httDomainConfigVo) {
		Result result = new Result();
		try {
			if(httDomainConfigVo.getStatus() == null){
				result.setError(ResultCode.CODE_STATE_4006, "操作失败");
				return result;
			}
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			httDomainConfigVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			httDomainConfigVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.httDomainConfigService.edit(httDomainConfigVo);
			HttDomainConfigSearch search = new HttDomainConfigSearch();
			search.setFlag(httDomainConfigVo.getFlag());
			List<HttDomainConfigVo> list = this.httDomainConfigService.getList(search);
			for (HttDomainConfigVo obj : list) {
				if(httDomainConfigVo.getStatus() == 2 && !obj.getId().equals(httDomainConfigVo.getId()) 
						&& obj.getStatus() == 2) {
					obj.setStatus(1);	//全部下线
					obj.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
					obj.setUpdateDate(DateUtil2.getCurrentLongDateTime());
					this.httDomainConfigService.edit(obj);
				}
			}
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
