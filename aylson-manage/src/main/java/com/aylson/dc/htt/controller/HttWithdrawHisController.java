package com.aylson.dc.htt.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.aylson.dc.htt.search.HttWithdrawHisSearch;
import com.aylson.dc.htt.service.HttWithdrawHisService;
import com.aylson.dc.htt.vo.HttWithdrawHisVo;
import com.aylson.dc.sys.common.SessionInfo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.StrUtil;
import com.aylson.utils.StringUtil;

import net.sf.json.JSONObject;

/**
 * 用户提现审核管理
 * 
 * @author Minbo
 */
@Controller
@RequestMapping("/htt/httWithdrawHis")
public class HttWithdrawHisController extends BaseController {

	protected static final Logger logger = Logger.getLogger(HttWithdrawHisController.class);

	@Autowired
	private HttWithdrawHisService httWithdrawHisService;

	/**
	 * 后台-首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/toIndex")
	public String toIndex() {
		return "/jsp/htt/admin/httWithdrawHis/index";
	}

	/**
	 * 获取列表
	 * 
	 * @return list
	 */
	@RequestMapping(value = "/admin/list")
	@ResponseBody
	public EasyuiDataGridJson list(HttWithdrawHisSearch httWithdrawHisSearch) {
		EasyuiDataGridJson result = new EasyuiDataGridJson();// 页面DataGrid结果集
		try {
			httWithdrawHisSearch.setIsPage(true);
			List<HttWithdrawHisVo> list = this.httWithdrawHisService.getList(httWithdrawHisSearch);
			result.setTotal(this.httWithdrawHisService.getRowCount(httWithdrawHisSearch));
			result.setRows(list);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getList")
	public List<HttWithdrawHisVo> getList(HttWithdrawHisSearch HttWithdrawHisSearch) {
		List<HttWithdrawHisVo> list = this.httWithdrawHisService.getList(HttWithdrawHisSearch);
		return list;
	}

	/**
	 * 后台-编辑页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/toEdit")
	public String toEdit(String id) {
		if (id != null) {
			HttWithdrawHisVo httWithdrawHisVo = this.httWithdrawHisService.getById(id);
			this.request.setAttribute("httWithdrawHisVo", httWithdrawHisVo);
		}
		return "/jsp/htt/admin/httWithdrawHis/add";
	}

	/**
	 * 后台-编辑保存
	 * 
	 * @param httWithdrawHisVo
	 * @return
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(HttWithdrawHisVo httWithdrawHisVo) {
		Result result = new Result();
		try {
			SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
			httWithdrawHisVo
					.setUpdatedBy(sessionInfo.getUser().getUserName() + "/" + sessionInfo.getRole().getRoleName());
			if(httWithdrawHisVo.getStatus().equals("处理中")) {
				httWithdrawHisVo.setStatusType(1);
			}else if(httWithdrawHisVo.getStatus().equals("提现成功")) {
				httWithdrawHisVo.setStatusType(2);
			}else if(httWithdrawHisVo.getStatus().equals("提现失败")) {
				httWithdrawHisVo.setStatusType(4);
			}else if(httWithdrawHisVo.getStatus().equals("审核中")) {
				httWithdrawHisVo.setStatusType(5);
			}else if(httWithdrawHisVo.getStatus().equals("审核成功-财务打款中，请耐心等候")) {
				httWithdrawHisVo.setStatusType(6);
			}else if(httWithdrawHisVo.getStatus().equals("审核失败")) {
				httWithdrawHisVo.setStatusType(7);
			}
			result = this.httWithdrawHisService.updateWithdrawHisInfo(httWithdrawHisVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}

	/**
	 * 处理Excel文件
	 * 
	 * @param httWithdrawHisSearch
	 * @param out
	 * @throws IOException
	 */
	public void processExcelFile(HttWithdrawHisSearch httWithdrawHisSearch, OutputStream out) throws IOException {
		List<HttWithdrawHisVo> list = this.getList(httWithdrawHisSearch);
		if (list != null && list.size() > 0) {
			// 创建一个工作簿
			HSSFWorkbook workBook = new HSSFWorkbook();
			// 创建一个工作表
			HSSFSheet sheet = workBook.createSheet();
			// 设置表格默认列宽度为15个字节
			sheet.setDefaultColumnWidth((short) 30);
			HSSFRow row = sheet.createRow(0);
			// 生成多少列
			int columNum = 12;
			HSSFCell cell[] = new HSSFCell[columNum];
			for (int i = 0; i < columNum; i++) {
				cell[i] = row.createCell(i);
			}
			cell[0].setCellValue("订单号码");
			cell[1].setCellValue("全局身份唯一ID");
			cell[2].setCellValue("申请人手机号码");
			cell[3].setCellValue("提现类型");
			cell[4].setCellValue("姓名");
			cell[5].setCellValue("账户名");
			cell[6].setCellValue("兑换金币");
			cell[7].setCellValue("提现金额");
			cell[8].setCellValue("提现发起时间");
			cell[9].setCellValue("提现状态");
			cell[10].setCellValue("完成时间");
			cell[11].setCellValue("打款失败原因");
			for (int i = 0; i < list.size(); i++) {
				HttWithdrawHisVo temp = list.get(i);
				HSSFRow row1 = sheet.createRow(i + 1);
				HSSFCell cellData[] = new HSSFCell[columNum];
				for (int j = 0; j < columNum; j++) {
					cellData[j] = row1.createCell(j);
				}
				cellData[0].setCellValue(temp.getId());
				cellData[1].setCellValue(temp.getPhoneNum());
				if (!StrUtil.null2Str(temp.getOwnPhoneNum()).equals("")) {
					cellData[2].setCellValue(temp.getOwnPhoneNum());
				} else {
					if (temp.getPhoneNum().length() == 11) {
						cellData[2].setCellValue(temp.getPhoneNum());
					}
				}
				cellData[3].setCellValue(temp.getWithdrawName());
				cellData[4].setCellValue(temp.getName());
				cellData[5].setCellValue(temp.getAccount());
				cellData[6].setCellValue(temp.getGold());
				cellData[7].setCellValue(temp.getIncome());
				cellData[8].setCellValue(temp.getWithdrawTime());
				cellData[9].setCellValue(temp.getStatus());
				cellData[10].setCellValue(temp.getUpdateDate());
				cellData[11].setCellValue(temp.getFailMsg());
			}
			workBook.write(out);
			out.flush();
			out.close();
			workBook.close();
		}
	}

	@RequestMapping(value = "/admin/exportApply", method = RequestMethod.GET)
	@ResponseBody
	public void exportApply(String applyIds, HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("application/vnd.ms-excel");
			String excelName = null;
			excelName = URLEncoder.encode("提现申请列表-" + DateUtil2.getCurrentDate(), "UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=" + excelName + ".xls");
			response.setCharacterEncoding("utf-8");
			OutputStream out = response.getOutputStream();
			if (StringUtil.isNotEmpty(applyIds)) {
				HttWithdrawHisSearch httWithdrawHisSearch = new HttWithdrawHisSearch();
				httWithdrawHisSearch.setIdsArray(applyIds.split(","));
				this.processExcelFile(httWithdrawHisSearch, out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/admin/exportApplyByDate", method = RequestMethod.GET)
	@ResponseBody
	public void exportApplyByDate(String withdrawTime, HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("application/vnd.ms-excel");
			String excelName = null;
			excelName = URLEncoder.encode("按日期提现-申请列表-" + DateUtil2.getCurrentDate(), "UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=" + excelName + ".xls");
			response.setCharacterEncoding("utf-8");
			OutputStream out = response.getOutputStream();
			if (withdrawTime.length() != 0) {
				HttWithdrawHisSearch httWithdrawHisSearch = new HttWithdrawHisSearch();
				httWithdrawHisSearch.setWithdrawTime(withdrawTime);
				httWithdrawHisSearch.setRows(5000);
				this.processExcelFile(httWithdrawHisSearch, out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/admin/exportApplyByStatus", method = RequestMethod.GET)
	@ResponseBody
	public void exportApplyByStatus(Integer statusType, HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("application/vnd.ms-excel");
			String excelName = null;
			excelName = URLEncoder.encode("按提现状态提现-申请列表-" + DateUtil2.getCurrentDate(), "UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=" + excelName + ".xls");
			response.setCharacterEncoding("utf-8");
			OutputStream out = response.getOutputStream();
			if (statusType != 0) {
				HttWithdrawHisSearch httWithdrawHisSearch = new HttWithdrawHisSearch();
				httWithdrawHisSearch.setStatusType(statusType);
				httWithdrawHisSearch.setRows(5000);
				this.processExcelFile(httWithdrawHisSearch, out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/admin/toImport", method = RequestMethod.GET)
	public String toImport() {
		return "/jsp/htt/admin/httWithdrawHis/import";
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
			logger.info("Excel中待更新记录条数：" + listob.size());

			long start = System.currentTimeMillis();

			List<String> idList = new ArrayList<String>();
			for (List<Object> list : listob) {
				idList.add(String.valueOf(list.get(0)));
			}

			List<HttWithdrawHisVo> httWithdrawHisVoList = this.httWithdrawHisService.selectByIdList(idList);
			logger.info("查询成功存在的待更新记录条数：" + httWithdrawHisVoList.size());

			List<HttWithdrawHisVo> httWithdrawHisVoListSuscess = new ArrayList<>();
			long sucessSum = 0;
			long failSum = 0;
			long notExistSum = 0;

			String statusSuccess = "审核成功-财务打款中，请耐心等候";
			
			for (List<Object> list : listob) {
				String orderId = String.valueOf(list.get(0));
				String status = String.valueOf(list.get(1)).trim();

				for (int i = 0; i < httWithdrawHisVoList.size(); i++) {
					HttWithdrawHisVo httWithdrawHisVo = httWithdrawHisVoList.get(i);
					if (httWithdrawHisVo != null) {
						if (httWithdrawHisVo.getId().equals(orderId)) {
							boolean flag = false;
							// 1=处理中；2=提现成功；4=提现失败；5=审核中；6=审核成功；7=审核失败
							if (status.equals("成功") && httWithdrawHisVo.getStatusType() == 1) {
								// 支付宝
								if (httWithdrawHisVo.getWithdrawType() == 2) {
									httWithdrawHisVo.setStatusType(6);
									httWithdrawHisVo.setStatus(statusSuccess);

									// 微信
								} else if (httWithdrawHisVo.getWithdrawType() == 3) {
									httWithdrawHisVo.setStatusType(6);
									httWithdrawHisVo.setStatus(statusSuccess);
								}
								flag = true;

							} else if (status.equals("失败") && httWithdrawHisVo.getStatusType() == 1) {
								httWithdrawHisVo.setStatusType(4);
								httWithdrawHisVo.setStatus("提现失败-核实数据不一致");
								flag = true;
							}

							if (flag) {
								httWithdrawHisVo.setUpdateDate(DateUtil2.getCurrentLongDateTime());
								SessionInfo sessionInfo = (SessionInfo) this.request.getSession()
										.getAttribute("sessionInfo");
								httWithdrawHisVo.setUpdatedBy(sessionInfo.getUser().getUserName() + "/"
										+ sessionInfo.getUser().getRoleName());

								if (httWithdrawHisVo.getStatusType() == 4) {
									Result result = this.httWithdrawHisService.updateWithdrawHisInfo(httWithdrawHisVo);
									if (result.getResultCode() != 200) {
										logger.info("订单号=" + httWithdrawHisVo.getId() + ", 更新结果result="
												+ result.toJsonString() + "，status=" + status + "，flag=" + flag);
									} else {
										failSum++;
									}
								} else {
									httWithdrawHisVoListSuscess.add(httWithdrawHisVo);
									sucessSum++;
								}
							}
							break; // 一旦遇到匹配id，则停止当前循环，进入下一个
						}
					} else {
						logger.warn("订单号=" + orderId + ", 无此记录，跳过处理。");
						notExistSum++;
					}
				}
			}

			if (httWithdrawHisVoListSuscess.size() > 0) {
				boolean flag = this.httWithdrawHisService.batchUpdate(httWithdrawHisVoListSuscess);
				logger.info("批量更新成功记录数据标识：flag=" + flag);
			}

			long end = System.currentTimeMillis();
			logger.info("总运行时间：" + (end - start) / 1000 + " s，更新的成功记录数sucessSum=" + sucessSum + ", 更新的失败记录数failSum="
					+ failSum + ", 不存在数据的记录数notExistSum=" + notExistSum);

			Map<String, Object> map = new HashMap<>();
			map.put("result", "成功");
			map.put("success", "0");
			JSONObject json = JSONObject.fromObject(map);
			in.close();
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
}
