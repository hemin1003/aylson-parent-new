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
import com.aylson.core.frame.domain.Result;
import com.aylson.core.frame.domain.ResultCode;
import com.aylson.dc.htt.search.HttBlacklistSetSearch;
import com.aylson.dc.htt.service.HttBlacklistSetService;
import com.aylson.dc.htt.vo.HttBlacklistSetVo;
import com.aylson.dc.sys.common.SessionInfo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;
import com.aylson.utils.UUIDUtils;

/**
 * 黑名单规则管理
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httBlacklistSet")
public class HttBlacklistSetController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(HttBlacklistSetController.class);

	@Autowired
	private HttBlacklistSetService httBlacklistSetService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/httBlacklistSet/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttBlacklistSetSearch blacklistSetSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			blacklistSetSearch.setIsPage(true);
			List<HttBlacklistSetVo> list = this.httBlacklistSetService.getList(blacklistSetSearch);
			result.setTotal(this.httBlacklistSetService.getRowCount(blacklistSetSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 后台-添加页面
	 * @param noticeConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/toAdd", method = RequestMethod.GET)
	public String toAdd(HttBlacklistSetVo blacklistSetVo) {
		this.request.setAttribute("blacklistSetVo", blacklistSetVo);
		return "/jsp/htt/admin/httBlacklistSet/add";
	}
	
	/**
	 * 后台-添加保存
	 * @param noticeConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(HttBlacklistSetVo blacklistSetVo) {
		Result result = new Result();
		try{
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			blacklistSetVo.setId(UUIDUtils.create());
			blacklistSetVo.setStatus(1);
			String cTime = DateUtil2.getCurrentLongDateTime();
			blacklistSetVo.setCreatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			blacklistSetVo.setCreateDate(cTime);
			blacklistSetVo.setUpdateDate(cTime);
			Boolean flag = this.httBlacklistSetService.add(blacklistSetVo);
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
			HttBlacklistSetVo httBlacklistSetVo = this.httBlacklistSetService.getById(id);
			this.request.setAttribute("httBlacklistSetVo", httBlacklistSetVo);
		}
		return "/jsp/htt/admin/httBlacklistSet/add";
	}
	
	/**
	 * 后台-编辑保存
	 * @param noticeConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(HttBlacklistSetVo httBlacklistSetVo) {
		Result result = new Result();
		try {
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			httBlacklistSetVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			httBlacklistSetVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.httBlacklistSetService.edit(httBlacklistSetVo);
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
			Boolean flag = this.httBlacklistSetService.deleteById(id);
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
	public Result changeStatus(HttBlacklistSetVo httBlacklistSetVo) {
		Result result = new Result();
		try {
			if(httBlacklistSetVo.getStatus() == null){
				result.setError(ResultCode.CODE_STATE_4006, "操作失败");
				return result;
			}
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			httBlacklistSetVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			httBlacklistSetVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.httBlacklistSetService.edit(httBlacklistSetVo);
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
