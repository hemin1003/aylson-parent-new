package com.aylson.dc.ytt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.ytt.dao.SpiderInfoDao;
import com.aylson.dc.ytt.po.SpiderInfo;
import com.aylson.dc.ytt.search.SpiderInfoSearch;

@Repository
public class SpiderInfoDaoImpl extends BaseDaoImpl<SpiderInfo, SpiderInfoSearch> implements SpiderInfoDao {

	@Override
	public long selectDaysOfNewsNum(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectDaysOfNewsNum"), params);
	}
	
	@Override
	public List<SpiderInfo> selectNewsNumOfTag(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectList(this.getSqlName("selectNewsNumOfTag"), params);
	}

	@Override
	public long selectAllNewsNum() {
		return this.sqlSessionTemplate.selectOne(this.getSqlName("selectAllNewsNum"));
	}
	
}
