package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttNoticeConfigDao;
import com.aylson.dc.htt.po.HttNoticeConfig;
import com.aylson.dc.htt.search.HttNoticeConfigSearch;
import com.aylson.dc.htt.service.HttNoticeConfigService;

@Service
public class HttNoticeConfigServiceImpl extends BaseServiceImpl<HttNoticeConfig, HttNoticeConfigSearch> implements HttNoticeConfigService {

	@Autowired
	private HttNoticeConfigDao shareNoticeConfigDao;

	@Override
	protected BaseDao<HttNoticeConfig, HttNoticeConfigSearch> getBaseDao() {
		return shareNoticeConfigDao;
	}
}
