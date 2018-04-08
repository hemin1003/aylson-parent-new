package com.aylson.dc.ytt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.ytt.dao.SpiderConfigDao;
import com.aylson.dc.ytt.po.SpiderConfig;
import com.aylson.dc.ytt.search.SpiderConfigSearch;
import com.aylson.dc.ytt.service.SpiderConfigService;

@Service
public class SpiderConfigServiceImpl extends BaseServiceImpl<SpiderConfig, SpiderConfigSearch> implements SpiderConfigService {

	@Autowired
	private SpiderConfigDao dao;

	@Override
	protected BaseDao<SpiderConfig, SpiderConfigSearch> getBaseDao() {
		return dao;
	}
	
}
