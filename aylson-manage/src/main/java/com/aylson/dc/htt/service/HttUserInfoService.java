package com.aylson.dc.htt.service;

import java.util.List;
import java.util.Map;

import com.aylson.core.frame.service.BaseService;
import com.aylson.dc.htt.po.HttUserInfo;
import com.aylson.dc.htt.search.HttUserInfoSearch;

public interface HttUserInfoService extends BaseService<HttUserInfo, HttUserInfoSearch> {
	
	/**
	 * 查指定字段数据
	 */
	public List<HttUserInfo> getNumsOfTag(Map<String, Object> params);
}
