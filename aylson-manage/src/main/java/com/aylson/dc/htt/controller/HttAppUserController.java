package com.aylson.dc.htt.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aylson.core.easyui.EasyuiDataGridJson;
import com.aylson.core.frame.controller.BaseController;
import com.aylson.core.frame.domain.Result;
import com.aylson.core.frame.domain.ResultCode;
import com.aylson.dc.htt.search.HttAppUserSearch;
import com.aylson.dc.htt.service.BigUserService;
import com.aylson.dc.htt.service.HttAppUserService;
import com.aylson.dc.htt.service.HttAppUsermultiService;
import com.aylson.dc.htt.vo.BigUserVo;
import com.aylson.dc.htt.vo.HttAppUserVo;
import com.aylson.dc.htt.vo.HttAppUsermultiVo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;

import net.sf.json.JSONObject;

/**
 * 用户信息管理
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/appUser")
public class HttAppUserController extends BaseController {
	
	protected static final Logger logger = Logger.getLogger(HttAppUserController.class);

	@Autowired
	private HttAppUserService appUserService;
	@Autowired
	private HttAppUsermultiService appUsermultiService;
	@Autowired
	private BigUserService bigUserService;
	
	/**
	 * 后台-首页
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex", method = RequestMethod.GET)
	public String toIndex() {
		return "/jsp/htt/admin/appUser/index";
	}
	
	/**
	 * 获取列表
	 * @return list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyuiDataGridJson list(HttAppUserSearch appUserSearch){
		EasyuiDataGridJson result = new EasyuiDataGridJson();//页面DataGrid结果集
		try{
			appUserSearch.setIsPage(true);
			if (!StrUtil.null2Str(appUserSearch.getIsOrderByStudents()).equals("") && appUserSearch.getIsOrderByStudents() == 1) {
				appUserSearch.setSort("students");
				appUserSearch.setOrder("desc");
			}
			List<HttAppUserVo> list = this.appUserService.getList(appUserSearch);
			result.setTotal(this.appUserService.getRowCount(appUserSearch));
			result.setRows(list);
			return result;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 后台-添加页面
	 * @param appUserVo
	 * @return
	 */
	@RequestMapping(value = "/admin/toAdd", method = RequestMethod.GET)
	public String toAdd(HttAppUserVo appUserVo) {
		this.request.setAttribute("appUserVo", appUserVo);
		return "/jsp/htt/admin/appUser/add";
	}
	
	
	/**
	 * 后台-编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/toEdit", method = RequestMethod.GET)
	public String toEdit(String phoneNum) {
		if(phoneNum != null){
			HttAppUserVo appUserVo = this.appUserService.getById(phoneNum);
			this.request.setAttribute("appUserVo", appUserVo);
		}
		return "/jsp/htt/admin/appUser/update";
	}
	
	/**
	 * 后台-编辑保存
	 * @param noticeConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(HttAppUserVo httAppUserVo) {
		Result result = new Result();
		try {
			httAppUserVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.appUserService.edit(httAppUserVo);
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
	
	@RequestMapping(value = "/admin/toShowMore", method = RequestMethod.GET)
	public String toShowMore(String phoneNum) {
		if(phoneNum != null){
			HttAppUserVo appUserVo = this.appUserService.getById(phoneNum);
			this.request.setAttribute("appUserVo", appUserVo);
		}
		return "/jsp/htt/admin/appUser/add";
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
			Boolean flag = this.appUserService.deleteById(id);
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
	
	@RequestMapping(value = "/admin/changeToBigUser", method = RequestMethod.POST)
	@ResponseBody
	public Result changeToBigUser(HttAppUserVo appUserVo) {
		Result result = new Result();
		try {
			if(appUserVo.getIsDaka() == null){
				result.setError(ResultCode.CODE_STATE_4006, "操作失败");
				return result;
			}
			boolean isDataFlag = false;
			String cTime = DateUtil2.getCurrentLongDateTime();
			if(appUserVo.getIsDaka() == 1) {
				BigUserVo bigUserVo = new BigUserVo();
				bigUserVo.setPhoneNum(appUserVo.getPhoneNum());
				bigUserVo.setInviteCode(appUserVo.getInviteCode());
				bigUserVo.setCreateDate(cTime);
				bigUserVo.setUpdateDate(cTime);
				isDataFlag = this.bigUserService.add(bigUserVo);
			}else if(appUserVo.getIsDaka() == 0) {
				isDataFlag = this.bigUserService.deleteById(appUserVo.getPhoneNum());
			}
			if(isDataFlag) {
				appUserVo.setUpdateDate(cTime);
				Boolean flag = this.appUserService.edit(appUserVo);
				if(flag){
					result.setOK(ResultCode.CODE_STATE_200, "操作成功");
				}else {
					result.setError(ResultCode.CODE_STATE_4006, "操作失败");
				}
			}else {
				result.setError(ResultCode.CODE_STATE_4006, "操作失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/admin/changeStatus", method = RequestMethod.POST)
	@ResponseBody
	public Result changeStatus(HttAppUserVo appUserVo) {
		Result result = new Result();
		try {
			if(appUserVo.getBlackList() == null){
				result.setError(ResultCode.CODE_STATE_4006, "操作失败");
				return result;
			}
			appUserVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			Boolean flag = this.appUserService.isBlackList(appUserVo);
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
	
	@RequestMapping(value = "/admin/toImport", method = RequestMethod.GET)
	public String toImport() {
		return "/jsp/htt/admin/appUser/import";
	}

	@RequestMapping(value = "/admin/importApply", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject importApply(@RequestParam(value = "excel") CommonsMultipartFile excel) {
		try {
			if (excel.isEmpty()) {
				logger.info("文件不存在！");
				throw new Exception("文件不存在！");
			}
			InputStream in = excel.getInputStream();
			List<List<Object>> listob = new ImportExcelUtil().getListByExcel(in, excel.getOriginalFilename());
			in.close();
			logger.info("待更新记录条数：" + listob.size());
			Result result = this.appUserService.importOfProcessBlackList(listob);
			Map<String, Object> map = new HashMap<>();
			if(result.getResultCode() == 200) {
				map.put("result", "成功");
				map.put("success", "0");
			}else {
				map.put("result", "失败");
				map.put("success", "1");
			}
			JSONObject json = JSONObject.fromObject(map);
			return json;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Map<String, Object> map = new HashMap<>();
			map.put("result", "失败");
			map.put("success", "1");
			JSONObject json = JSONObject.fromObject(map);
			return json;
		}
	}
	
	//解绑微信
	@RequestMapping(value = "/admin/invalidWechat", method = RequestMethod.POST)
	@ResponseBody
	public Result invalidWechat(String phoneNum) {
		Result result = new Result();
		try {
			HttAppUserVo httAppUserVo = appUserService.getById(phoneNum);
			httAppUserVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
			httAppUserVo.setIsBindWechat(0);
			Boolean flag = this.appUserService.edit(httAppUserVo);
			if(flag){
				HttAppUsermultiVo httAppUsermultiVo = appUsermultiService.getById(phoneNum);
				//失效wechatId
				String abandonWechatOpenId = httAppUsermultiVo.getWechatOpenId() + "_abandon";
				httAppUsermultiVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
				httAppUsermultiVo.setWechatOpenId(abandonWechatOpenId);
				flag = this.appUsermultiService.edit(httAppUsermultiVo);
				if (flag) {
					result.setOK(ResultCode.CODE_STATE_200, "操作成功");
				}else {
					result.setError(ResultCode.CODE_STATE_4006, "操作失败");
				}
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