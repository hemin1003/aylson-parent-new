package com.aylson.dc.htt.service;

import java.util.List;
import java.util.Map;

import com.aylson.core.frame.service.BaseService;
import com.aylson.dc.htt.po.ExpenseInfo;
import com.aylson.dc.htt.search.ExpenseInfoSearch;

public interface ExpenseInfoService extends BaseService<ExpenseInfo, ExpenseInfoSearch> {
	
	/**
	 * 查指定字段数据
	 */
	public List<ExpenseInfo> getNumsOfTag(Map<String, Object> params);
}
