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
import com.aylson.dc.htt.search.HttIncomeSetSearch;
import com.aylson.dc.htt.service.HttIncomeSetService;
import com.aylson.dc.htt.vo.HttIncomeSetVo;
import com.aylson.dc.sys.common.SessionInfo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.UUIDUtils;

/**
 * 提现金额配置
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httIncomeSet")
public class HttIncomeSetController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(HttIncomeSetController.class);

	@Autowired
	private HttIncomeSetService httIncomeSetService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/httIncomeSet/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttIncomeSetSearch httIncomeSetSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			httIncomeSetSearch.setIsPage(true);
			List<HttIncomeSetVo> list = this.httIncomeSetService.getList(httIncomeSetSearch);
			result.setTotal(this.httIncomeSetService.getRowCount(httIncomeSetSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 后台-添加页面
	 * @param httIncomeSetVo
	 * @return
	 */
	@RequestMapping(value = "/admin/toAdd", method = RequestMethod.GET)
	public String toAdd(HttIncomeSetVo httIncomeSetVo) {
		this.request.setAttribute("httIncomeSetVo", httIncomeSetVo);
		return "/jsp/htt/admin/httIncomeSet/add";
	}
	
	/**
	 * 后台-添加保存
	 * @param httIncomeSetVo
	 * @return
	 */
	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(HttIncomeSetVo httIncomeSetVo) {
		Result result = new Result();
		try{
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			httIncomeSetVo.setId(UUIDUtils.create());
			String cTime = DateUtil2.getCurrentLongDateTime();
			httIncomeSetVo.setCreatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			httIncomeSetVo.setCreateDate(cTime);
			httIncomeSetVo.setUpdateDate(cTime);
			Boolean flag = this.httIncomeSetService.add(httIncomeSetVo);
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
			HttIncomeSetVo httIncomeSetVo = this.httIncomeSetService.getById(id);
			this.request.setAttribute("httIncomeSetVo", httIncomeSetVo);
		}
		return "/jsp/htt/admin/httIncomeSet/add";
	}
	
	/**
	 * 后台-编辑保存
	 * @param httIncomeSetVo
	 * @return
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(HttIncomeSetVo httIncomeSetVo) {
		Result result = new Result();
		try {
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			httIncomeSetVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			httIncomeSetVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.httIncomeSetService.edit(httIncomeSetVo);
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
			Boolean flag = this.httIncomeSetService.deleteById(id);
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
	public Result changeStatus(HttIncomeSetVo httIncomeSetVo) {
		Result result = new Result();
		try {
			if(httIncomeSetVo.getStatus() == null){
				result.setError(ResultCode.CODE_STATE_4006, "操作失败");
				return result;
			}
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			httIncomeSetVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			httIncomeSetVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.httIncomeSetService.edit(httIncomeSetVo);
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
