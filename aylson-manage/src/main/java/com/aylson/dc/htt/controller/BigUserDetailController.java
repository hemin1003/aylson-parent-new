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
import com.aylson.dc.htt.search.HttAppUserSearch;
import com.aylson.dc.htt.service.HttAppUserService;
import com.aylson.dc.htt.vo.HttAppUserVo;
import com.aylson.utils.DateUtil2;

/**
 * 大咖用户数据明细
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/bigUserDetail")
public class BigUserDetailController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(BigUserDetailController.class);

	@Autowired
	private HttAppUserService bigUserDetailService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/bigUserDetail/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttAppUserSearch bigUserDetailSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			bigUserDetailSearch.setIsPage(true);
			bigUserDetailSearch.setIsDaka(1);
			List<HttAppUserVo> list = this.bigUserDetailService.getList(bigUserDetailSearch);
			result.setTotal(this.bigUserDetailService.getRowCount(bigUserDetailSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 后台-添加页面
	 * @param bigUserDetailVo
	 * @return
	 */
	@RequestMapping(value = "/admin/toAdd", method = RequestMethod.GET)
	public String toAdd(HttAppUserVo bigUserDetailVo) {
		this.request.setAttribute("bigUserDetailVo", bigUserDetailVo);
		return "/jsp/htt/admin/bigUserDetail/add";
	}
	
	
	/**
	 * 后台-编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/toEdit", method = RequestMethod.GET)
	public String toEdit(String phoneNum) {
		if(phoneNum != null){
			HttAppUserVo bigUserDetailVo = this.bigUserDetailService.getById(phoneNum);
			this.request.setAttribute("bigUserDetailVo", bigUserDetailVo);
		}
		return "/jsp/htt/admin/bigUserDetail/add";
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
			Boolean flag = this.bigUserDetailService.deleteById(id);
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
	public Result changeStatus(HttAppUserVo bigUserDetailVo) {
		Result result = new Result();
		try {
			if(bigUserDetailVo.getBlackList() == null){
				result.setError(ResultCode.CODE_STATE_4006, "操作失败");
				return result;
			}
			bigUserDetailVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.bigUserDetailService.edit(bigUserDetailVo);
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
