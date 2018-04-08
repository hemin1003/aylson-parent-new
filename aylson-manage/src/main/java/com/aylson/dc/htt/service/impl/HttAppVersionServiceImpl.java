package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttAppVersionDao;
import com.aylson.dc.htt.po.HttAppVersion;
import com.aylson.dc.htt.search.HttAppVersionSearch;
import com.aylson.dc.htt.service.HttAppVersionService;

@Service
public class HttAppVersionServiceImpl  extends BaseServiceImpl<HttAppVersion, HttAppVersionSearch> implements HttAppVersionService {

	@Autowired
	private HttAppVersionDao httAppVersionDao;

	@Override
	protected BaseDao<HttAppVersion, HttAppVersionSearch> getBaseDao() {
		return httAppVersionDao;
	}
}
