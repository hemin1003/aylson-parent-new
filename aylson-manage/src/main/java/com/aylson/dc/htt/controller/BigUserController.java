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
import com.aylson.dc.htt.search.BigUserSearch;
import com.aylson.dc.htt.service.BigUserService;
import com.aylson.dc.htt.vo.BigUserVo;
import com.aylson.dc.sys.common.SessionInfo;
import com.aylson.utils.DateUtil2;

/**
 * 大咖用户管理
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/bigUser")
public class BigUserController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(BigUserController.class);

	@Autowired
	private BigUserService bigUserService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/bigUser/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(BigUserSearch bigUserSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			bigUserSearch.setIsPage(true);
			List<BigUserVo> list = this.bigUserService.getList(bigUserSearch);
			result.setTotal(this.bigUserService.getRowCount(bigUserSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 后台-添加页面
	 * @param bigUserVo
	 * @return
	 */
	@RequestMapping(value = "/admin/toAdd", method = RequestMethod.GET)
	public String toAdd(BigUserVo bigUserVo) {
		this.request.setAttribute("bigUserVo", bigUserVo);
		return "/jsp/htt/admin/bigUser/add";
	}
	
	/**
	 * 后台-编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/toEdit", method = RequestMethod.GET)
	public String toEdit(String phoneNum) {
		if(phoneNum != null){
			BigUserVo bigUserVo = this.bigUserService.getById(phoneNum);
			this.request.setAttribute("bigUserVo", bigUserVo);
		}
		return "/jsp/htt/admin/bigUser/add";
	}
	
	/**
	 * 后台-绑定页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/toBind", method = RequestMethod.GET)
	public String toBind(String phoneNum) {
		if(phoneNum != null){
			BigUserVo bigUserVo = this.bigUserService.getById(phoneNum);
			this.request.setAttribute("bigUserVo", bigUserVo);
		}
		return "/jsp/htt/admin/bigUser/bind";
	}
	
	/**
	 * 后台-添加保存
	 * @param appConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(BigUserVo bigUserVo) {
		Result result = new Result();
		try{
			int status = this.bigUserService.addUser(bigUserVo, this.request);
			if(status == 1){
				result.setError(ResultCode.CODE_STATE_4006, "用户已存在");
			}else if(status == 2){
				result.setError(ResultCode.CODE_STATE_4006, "邀请码生成失败");
			}else if(status == 0){
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
			}else {
				result.setError(ResultCode.CODE_STATE_4006, "操作失败");
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 后台-编辑保存
	 * @param appConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(BigUserVo bigUserVo) {
		Result result = new Result();
		try {
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			bigUserVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			bigUserVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.bigUserService.edit(bigUserVo);
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
			Boolean flag = this.bigUserService.deleteById(id);
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
	public Result changeStatus(BigUserVo bigUserVo) {
		Result result = new Result();
		try {
			bigUserVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.bigUserService.edit(bigUserVo);
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
