package com.aylson.dc.htt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttUserInfoDao;
import com.aylson.dc.htt.po.HttUserInfo;
import com.aylson.dc.htt.search.HttUserInfoSearch;
import com.aylson.dc.htt.service.HttUserInfoService;

@Service
public class HttUserInfoServiceImpl extends BaseServiceImpl<HttUserInfo, HttUserInfoSearch>
		implements HttUserInfoService {

	@Autowired
	private HttUserInfoDao dao;

	@Override
	protected BaseDao<HttUserInfo, HttUserInfoSearch> getBaseDao() {
		return dao;
	}

	@Override
	public List<HttUserInfo> getNumsOfTag(Map<String, Object> params) {
		return this.dao.selectNumsOfTag(params);
	}
}
