package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttInitConfigDao;
import com.aylson.dc.htt.po.HttInitConfig;
import com.aylson.dc.htt.search.HttInitConfigSearch;
import com.aylson.dc.htt.service.HttInitConfigService;

@Service
public class HttInitConfigServiceImpl  extends BaseServiceImpl<HttInitConfig, HttInitConfigSearch> implements HttInitConfigService {

	@Autowired
	private HttInitConfigDao initConfigDao;

	@Override
	protected BaseDao<HttInitConfig, HttInitConfigSearch> getBaseDao() {
		return initConfigDao;
	}
}
