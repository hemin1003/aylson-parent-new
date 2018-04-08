package com.aylson.dc.htt.dao;

import java.util.List;
import java.util.Map;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.dc.htt.po.ExpenseInfo;
import com.aylson.dc.htt.search.ExpenseInfoSearch;

public interface ExpenseInfoDao extends BaseDao<ExpenseInfo, ExpenseInfoSearch> {
	
	/**
	 * 查指定字段数据
	 */
	public List<ExpenseInfo> selectNumsOfTag(Map<String, Object> params);
	
}
