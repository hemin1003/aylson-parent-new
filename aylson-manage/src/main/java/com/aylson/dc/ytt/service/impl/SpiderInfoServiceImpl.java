package com.aylson.dc.ytt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.ytt.dao.SpiderInfoDao;
import com.aylson.dc.ytt.po.SpiderInfo;
import com.aylson.dc.ytt.search.SpiderInfoSearch;
import com.aylson.dc.ytt.service.SpiderInfoService;

@Service
public class SpiderInfoServiceImpl extends BaseServiceImpl<SpiderInfo, SpiderInfoSearch> implements SpiderInfoService {

	@Autowired
	private SpiderInfoDao dao;

	@Override
	protected BaseDao<SpiderInfo, SpiderInfoSearch> getBaseDao() {
		return dao;
	}
	
	/**
	 * 指定日期新增新闻数
	 */
	public long selectDaysOfNewsNum(Map<String, Object> params) {
		return this.dao.selectDaysOfNewsNum(params);
	}
	
	/**
	 * 查指定新闻类型数据
	 */
	public List<SpiderInfo> selectNewsNumOfTag(Map<String, Object> params){
		return this.dao.selectNewsNumOfTag(params);
	}

	/**
	 * 查总文章数
	 */
	public long selectAllNewsNum() {
		return this.dao.selectAllNewsNum();
	}
}