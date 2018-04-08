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
import com.aylson.dc.htt.search.HttWithdrawHisSearch;
import com.aylson.dc.htt.service.HttWithdrawHisService;
import com.aylson.dc.htt.vo.HttWithdrawHisVo;
import com.aylson.dc.sys.common.SessionInfo;
import com.aylson.utils.BillNumUtils;
import com.aylson.utils.DateUtil2;

/**
 * 大咖提现审核管理
 * 
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/bigUserWithdraw")
public class BigUserWithdrawController extends BaseController {

	protected static final Logger logger = Logger.getLogger(BigUserWithdrawController.class);

	@Autowired
	private HttWithdrawHisService bigUserWithdrawService;

	/**
	 * 后台-首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex")
	public String toIndex() {
		return "/jsp/htt/admin/bigUserWithdraw/index";
	}

	/**
	 * 获取列表
	 * 
	 * @return list
	 */
	@RequestMapping(value = "/admin/list")
	@ResponseBody
	public EasyuiDataGridJson list(HttWithdrawHisSearch bigUserWithdrawSearch) {
		EasyuiDataGridJson result = new EasyuiDataGridJson();// 页面DataGrid结果集
		try {
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			bigUserWithdrawSearch.setSysUserId(sessionInfo.getUser().getUserName());
			bigUserWithdrawSearch.setIsPage(true);
			List<HttWithdrawHisVo> list = this.bigUserWithdrawService.getList(bigUserWithdrawSearch);
			result.setTotal(this.bigUserWithdrawService.getRowCount(bigUserWithdrawSearch));
			result.setRows(list);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getList")
	public List<HttWithdrawHisVo> getList(HttWithdrawHisSearch bigUserWithdrawSearch) {
		SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
		bigUserWithdrawSearch.setSysUserId(sessionInfo.getUser().getUserName());
		List<HttWithdrawHisVo> list = this.bigUserWithdrawService.getList(bigUserWithdrawSearch);
		return list;
	}

	/**
	 * 后台-添加页面
	 * @param bigUserWithdrawVo
	 * @return
	 */
	@RequestMapping(value = "/admin/toAdd", method = RequestMethod.GET)
	public String toAdd(HttWithdrawHisVo bigUserWithdrawVo) {
		this.request.setAttribute("httWithdrawHisVo", bigUserWithdrawVo);
		return "/jsp/htt/admin/bigUserWithdraw/add";
	}
	
	/**
	 * 后台-添加保存
	 * @param appConfigVo
	 * @return
	 */
	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(HttWithdrawHisVo bigUserWithdrawVo) {
		Result result = new Result();
		try{
			SessionInfo sessionInfo = (SessionInfo)this.request.getSession().getAttribute("sessionInfo");
			bigUserWithdrawVo.setId(BillNumUtils.getBillCode());
			String cTime = DateUtil2.getCurrentLongDateTime();
			bigUserWithdrawVo.setWithdrawName(this.getWithdrawName(bigUserWithdrawVo.getWithdrawType()));
			bigUserWithdrawVo.setStatus("处理中");
			bigUserWithdrawVo.setStatusType(1);
			bigUserWithdrawVo.setCreatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getUser().getRoleName());
			bigUserWithdrawVo.setCreateDate(cTime);
			bigUserWithdrawVo.setUpdateDate(cTime);
			bigUserWithdrawVo.setWithdrawTime(cTime);
			bigUserWithdrawVo.setSysUserId(sessionInfo.getUser().getUserName());
			Boolean flag = this.bigUserWithdrawService.add(bigUserWithdrawVo);
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
	 * 解析提现名称
	 * @param withdrawType
	 * 1=手机充值;2=支付宝;3=微信;4=QQ币
	 */
	private String getWithdrawName(int withdrawType) {
		switch (withdrawType) {
			case 1:
				return "手机话费";
			case 2:
				return "支付宝";
			case 3:
				return "微信";
			case 4:
				return "QQ币";
			default:
				throw new RuntimeException("未知提现类型 withdrawType值");
		}
	}
}