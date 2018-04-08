package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttAppConfigDao;
import com.aylson.dc.htt.po.HttAppConfig;
import com.aylson.dc.htt.search.HttAppConfigSearch;
import com.aylson.dc.htt.service.HttAppConfigService;

@Service
public class HttAppConfigServiceImpl  extends BaseServiceImpl<HttAppConfig, HttAppConfigSearch> implements HttAppConfigService {

	@Autowired
	private HttAppConfigDao appConfigDao;

	@Override
	protected BaseDao<HttAppConfig, HttAppConfigSearch> getBaseDao() {
		return appConfigDao;
	}
}
