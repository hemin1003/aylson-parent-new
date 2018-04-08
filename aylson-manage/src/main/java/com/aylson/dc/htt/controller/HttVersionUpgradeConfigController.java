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
import com.aylson.dc.htt.search.HttVersionUpgradeConfigSearch;
import com.aylson.dc.htt.service.HttVersionUpgradeConfigService;
import com.aylson.dc.htt.vo.HttVersionUpgradeConfigVo;
import com.aylson.dc.sys.common.SessionInfo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.UUIDUtils;

/**
 * 公告信息记录
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httVersionUpgradeConfig")
public class HttVersionUpgradeConfigController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(HttVersionUpgradeConfigController.class);

	@Autowired
	private HttVersionUpgradeConfigService versionUpgradeConfigService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/httVersionUpgradeConfig/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttVersionUpgradeConfigSearch versionUpgradeConfigSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			versionUpgradeConfigSearch.setIsPage(true);
			List<HttVersionUpgradeConfigVo> list = this.versionUpgradeConfigService.getList(versionUpgradeConfigSearch);
			result.setTotal(this.versionUpgradeConfigService.getRowCount(versionUpgradeConfigSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 后台-添加页面
	 * @param versionUpgradeConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/toAdd", method = RequestMethod.GET)
	public String toAdd(HttVersionUpgradeConfigVo versionUpgradeConfigVo) {
		this.request.setAttribute("versionUpgradeConfigVo", versionUpgradeConfigVo);
		return "/jsp/htt/admin/httVersionUpgradeConfig/add";
	}
	
	/**
	 * 后台-添加保存
	 * @param versionUpgradeConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(HttVersionUpgradeConfigVo versionUpgradeConfigVo) {
		Result result = new Result();
		try{
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			
			versionUpgradeConfigVo.setId(UUIDUtils.create());
			String cTime = DateUtil2.getCurrentLongDateTime();
			versionUpgradeConfigVo.setCreatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			versionUpgradeConfigVo.setCreateDate(cTime);
			versionUpgradeConfigVo.setUpdateDate(cTime);
			Boolean flag = this.versionUpgradeConfigService.add(versionUpgradeConfigVo);
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
			HttVersionUpgradeConfigVo versionUpgradeConfigVo = this.versionUpgradeConfigService.getById(id);
			this.request.setAttribute("versionUpgradeConfigVo", versionUpgradeConfigVo);
		}
		return "/jsp/htt/admin/httVersionUpgradeConfig/add";
	}
	
	/**
	 * 后台-编辑保存
	 * @param noticeConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(HttVersionUpgradeConfigVo versionUpgradeConfigVo) {
		Result result = new Result();
		try {
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			versionUpgradeConfigVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			versionUpgradeConfigVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.versionUpgradeConfigService.edit(versionUpgradeConfigVo);
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
			Boolean flag = this.versionUpgradeConfigService.deleteById(id);
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
