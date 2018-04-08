package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttVersionUpgradeConfigDao;
import com.aylson.dc.htt.po.HttVersionUpgradeConfig;
import com.aylson.dc.htt.search.HttVersionUpgradeConfigSearch;
import com.aylson.dc.htt.service.HttVersionUpgradeConfigService;

@Service
public class HttVersionUpgradeConfigServiceImpl extends BaseServiceImpl<HttVersionUpgradeConfig, HttVersionUpgradeConfigSearch> implements HttVersionUpgradeConfigService {

	@Autowired
	private HttVersionUpgradeConfigDao shareVersionUpgradeConfigDao;

	@Override
	protected BaseDao<HttVersionUpgradeConfig, HttVersionUpgradeConfigSearch> getBaseDao() {
		return shareVersionUpgradeConfigDao;
	}
}
