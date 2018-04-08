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
import com.aylson.dc.htt.search.HttNoticeConfigSearch;
import com.aylson.dc.htt.service.HttNoticeConfigService;
import com.aylson.dc.htt.vo.HttNoticeConfigVo;
import com.aylson.dc.sys.common.SessionInfo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.UUIDUtils;

/**
 * 公告信息记录
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httNoticeConfig")
public class HttNoticeConfigController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(HttNoticeConfigController.class);

	@Autowired
	private HttNoticeConfigService noticeConfigService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/noticeConfig/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttNoticeConfigSearch noticeConfigSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			noticeConfigSearch.setIsPage(true);
			List<HttNoticeConfigVo> list = this.noticeConfigService.getList(noticeConfigSearch);
			result.setTotal(this.noticeConfigService.getRowCount(noticeConfigSearch));
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
	public String toAdd(HttNoticeConfigVo noticeConfigVo) {
		this.request.setAttribute("noticeConfigVo", noticeConfigVo);
		return "/jsp/htt/admin/noticeConfig/add";
	}
	
	/**
	 * 后台-添加保存
	 * @param noticeConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(HttNoticeConfigVo noticeConfigVo) {
		Result result = new Result();
		try{
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			noticeConfigVo.setId(UUIDUtils.create());
			String cTime = DateUtil2.getCurrentLongDateTime();
			noticeConfigVo.setCreatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			noticeConfigVo.setCreateDate(cTime);
			noticeConfigVo.setUpdateDate(cTime);
			Boolean flag = this.noticeConfigService.add(noticeConfigVo);
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
			HttNoticeConfigVo noticeConfigVo = this.noticeConfigService.getById(id);
			this.request.setAttribute("noticeConfigVo", noticeConfigVo);
		}
		return "/jsp/htt/admin/noticeConfig/add";
	}
	
	/**
	 * 后台-编辑保存
	 * @param noticeConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(HttNoticeConfigVo noticeConfigVo) {
		Result result = new Result();
		try {
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			noticeConfigVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			noticeConfigVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.noticeConfigService.edit(noticeConfigVo);
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
			Boolean flag = this.noticeConfigService.deleteById(id);
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
	public Result changeStatus(HttNoticeConfigVo httNoticeConfigVo) {
		Result result = new Result();
		try {
			if(httNoticeConfigVo.getStatus() == null){
				result.setError(ResultCode.CODE_STATE_4006, "操作失败");
				return result;
			}
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			httNoticeConfigVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			httNoticeConfigVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.noticeConfigService.edit(httNoticeConfigVo);
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
