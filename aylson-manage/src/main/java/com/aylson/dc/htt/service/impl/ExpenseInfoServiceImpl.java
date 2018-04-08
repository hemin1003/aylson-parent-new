package com.aylson.dc.htt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.ExpenseInfoDao;
import com.aylson.dc.htt.po.ExpenseInfo;
import com.aylson.dc.htt.search.ExpenseInfoSearch;
import com.aylson.dc.htt.service.ExpenseInfoService;

@Service
public class ExpenseInfoServiceImpl  extends BaseServiceImpl<ExpenseInfo, ExpenseInfoSearch> implements ExpenseInfoService {

	@Autowired
	private ExpenseInfoDao dao;

	@Override
	protected BaseDao<ExpenseInfo, ExpenseInfoSearch> getBaseDao() {
		return dao;
	}

	@Override
	public List<ExpenseInfo> getNumsOfTag(Map<String, Object> params) {
		return this.dao.selectNumsOfTag(params);
	}
}
