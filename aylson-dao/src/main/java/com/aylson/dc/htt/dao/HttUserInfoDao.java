package com.aylson.dc.htt.dao;

import java.util.List;
import java.util.Map;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.dc.htt.po.HttUserInfo;
import com.aylson.dc.htt.search.HttUserInfoSearch;

public interface HttUserInfoDao extends BaseDao<HttUserInfo, HttUserInfoSearch> {
	
	/**
	 * 查指定字段数据
	 */
	public List<HttUserInfo> selectNumsOfTag(Map<String, Object> params);
	
}
