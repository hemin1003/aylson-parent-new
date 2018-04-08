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
import com.aylson.dc.htt.search.HttInviteUserHisSearch;
import com.aylson.dc.htt.service.HttInviteUserHisService;
import com.aylson.dc.htt.vo.HttInviteUserHisVo;
import com.aylson.dc.sys.common.SessionInfo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;
import com.aylson.utils.UUIDUtils;

/**
 * 邀请徒弟明细记录
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/inviteUserHis")
public class HttInviteUserHisController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(HttInviteUserHisController.class);

	@Autowired
	private HttInviteUserHisService inviteUserHisService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/inviteUserHis/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttInviteUserHisSearch inviteUserHisSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			inviteUserHisSearch.setIsPage(true);
			if(!StrUtil.null2Str(inviteUserHisSearch.getCreateDate()).equals("")) {
				inviteUserHisSearch.setTomorrowDate(DateUtil2.formatDate(DateUtils.
						addDays(DateUtil2.parseDate(inviteUserHisSearch.getCreateDate()), 1)));
			}
			List<HttInviteUserHisVo> list = this.inviteUserHisService.getList(inviteUserHisSearch);
			result.setTotal(this.inviteUserHisService.getRowCount(inviteUserHisSearch));
			result.setRows(list);	
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 后台-添加页面
	 * @param inviteUserHisVo
	 * @return
	 */
	@RequestMapping(value = "/admin/toAdd", method = RequestMethod.GET)
	public String toAdd(HttInviteUserHisVo inviteUserHisVo) {
		this.request.setAttribute("inviteUserHisVo", inviteUserHisVo);
		return "/jsp/htt/admin/inviteUserHis/add";
	}
	
	/**
	 * 后台-添加保存
	 * @param inviteUserHisVo
	 * @return
	 */
	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(HttInviteUserHisVo inviteUserHisVo) {
		Result result = new Result();
		try{
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			inviteUserHisVo.setId(UUIDUtils.create());
			String cTime = DateUtil2.getCurrentLongDateTime();
			inviteUserHisVo.setCreatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			inviteUserHisVo.setCreateDate(cTime);
			inviteUserHisVo.setUpdateDate(cTime);
			Boolean flag = this.inviteUserHisService.add(inviteUserHisVo);
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
			HttInviteUserHisVo inviteUserHisVo = this.inviteUserHisService.getById(id);
			this.request.setAttribute("inviteUserHisVo", inviteUserHisVo);
		}
		return "/jsp/htt/admin/inviteUserHis/add";
	}
	
	/**
	 * 后台-编辑保存
	 * @param inviteUserHisVo
	 * @return
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(HttInviteUserHisVo inviteUserHisVo) {
		Result result = new Result();
		try {
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			inviteUserHisVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			inviteUserHisVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.inviteUserHisService.edit(inviteUserHisVo);
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
			Boolean flag = this.inviteUserHisService.deleteById(id);
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
