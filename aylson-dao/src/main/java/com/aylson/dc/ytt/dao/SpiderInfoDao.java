package com.aylson.dc.ytt.dao;

import java.util.List;
import java.util.Map;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.dc.ytt.po.SpiderInfo;
import com.aylson.dc.ytt.search.SpiderInfoSearch;

public interface SpiderInfoDao extends BaseDao<SpiderInfo, SpiderInfoSearch> {
	
	/**
	 * 指定日期新增新闻数
	 */
	public long selectDaysOfNewsNum(Map<String, Object> params);
	
	/**
	 * 查指定新闻类型数据
	 */
	public List<SpiderInfo> selectNewsNumOfTag(Map<String, Object> params);
	
	/**
	 * 查总文章数
	 */
	public long selectAllNewsNum();
	
}
