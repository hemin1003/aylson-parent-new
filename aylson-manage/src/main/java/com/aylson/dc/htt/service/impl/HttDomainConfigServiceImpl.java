package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttDomainConfigDao;
import com.aylson.dc.htt.po.HttDomainConfig;
import com.aylson.dc.htt.search.HttDomainConfigSearch;
import com.aylson.dc.htt.service.HttDomainConfigService;

@Service
public class HttDomainConfigServiceImpl  extends BaseServiceImpl<HttDomainConfig, HttDomainConfigSearch> implements HttDomainConfigService {

	@Autowired
	private HttDomainConfigDao httDomainConfigDao;

	@Override
	protected BaseDao<HttDomainConfig, HttDomainConfigSearch> getBaseDao() {
		return httDomainConfigDao;
	}
}
