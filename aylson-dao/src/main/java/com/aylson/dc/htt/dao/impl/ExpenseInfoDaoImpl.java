package com.aylson.dc.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.ExpenseInfoDao;
import com.aylson.dc.htt.po.ExpenseInfo;
import com.aylson.dc.htt.search.ExpenseInfoSearch;

@Repository
public class ExpenseInfoDaoImpl extends BaseDaoImpl<ExpenseInfo, ExpenseInfoSearch> implements ExpenseInfoDao {


	@Override
	public List<ExpenseInfo> selectNumsOfTag(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectList(this.getSqlName("selectNumsOfTag"), params);
	}
}
