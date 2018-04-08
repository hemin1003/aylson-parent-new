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
import com.aylson.dc.htt.search.HttLoginHisSearch;
import com.aylson.dc.htt.service.HttLoginHisService;
import com.aylson.dc.htt.service.HttLoginnewInstalledHisService;
import com.aylson.dc.htt.vo.HttLoginHisVo;
import com.aylson.dc.htt.vo.HttLoginnewInstalledHisVo;
import com.aylson.dc.sys.common.SessionInfo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.UUIDUtils;

/**
 * 用户登录设备历史记录
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httLoginHis")
public class HttLoginHisController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(HttLoginHisController.class);

	@Autowired
	private HttLoginHisService loginHisService;
	
	@Autowired
	private HttLoginnewInstalledHisService loginnewInstalledHisService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/loginHis/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttLoginHisSearch loginHisSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			loginHisSearch.setIsPage(true);
			List<HttLoginHisVo> list = this.loginHisService.getList(loginHisSearch);
			result.setTotal(this.loginHisService.getRowCount(loginHisSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 后台-添加页面
	 * @param loginHisVo
	 * @return
	 */
	@RequestMapping(value = "/admin/toAdd", method = RequestMethod.GET)
	public String toAdd(HttLoginHisVo loginHisVo) {
		this.request.setAttribute("loginHisVo", loginHisVo);
		return "/jsp/htt/admin/loginHis/add";
	}
	
	/**
	 * 后台-添加保存
	 * @param loginHisVo
	 * @return
	 */
	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(HttLoginHisVo loginHisVo) {
		Result result = new Result();
		try{
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			loginHisVo.setId(UUIDUtils.create());
			String cTime = DateUtil2.getCurrentLongDateTime();
			loginHisVo.setCreatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			loginHisVo.setCreateDate(cTime);
			loginHisVo.setUpdateDate(cTime);
			Boolean flag = this.loginHisService.add(loginHisVo);
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
			HttLoginHisVo loginHisVo = this.loginHisService.getById(id);
			HttLoginnewInstalledHisVo loginnewInstalledHisVo = this.loginnewInstalledHisService.getById(id);
//			if(!StrUtil.null2Str(loginnewInstalledHisVo.getInstalledList()).equals("")) {
//				loginnewInstalledHisVo.setInstalledList(loginnewInstalledHisVo.getInstalledList().replaceAll("\"", ""));
//			}
			this.request.setAttribute("loginHisVo", loginHisVo);
			this.request.setAttribute("loginnewInstalledHisVo", loginnewInstalledHisVo);
		}
		return "/jsp/htt/admin/loginHis/add";
	}
	
	/**
	 * 后台-编辑保存
	 * @param loginHisVo
	 * @return
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(HttLoginHisVo loginHisVo) {
		Result result = new Result();
		try {
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			loginHisVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			loginHisVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.loginHisService.edit(loginHisVo);
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
			Boolean flag = this.loginHisService.deleteById(id);
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
