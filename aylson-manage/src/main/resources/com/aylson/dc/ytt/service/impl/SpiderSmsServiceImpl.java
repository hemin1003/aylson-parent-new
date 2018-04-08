package com.aylson.dc.ytt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.ytt.dao.SpiderSmsDao;
import com.aylson.dc.ytt.po.SpiderSms;
import com.aylson.dc.ytt.search.SpiderSmsSearch;
import com.aylson.dc.ytt.service.SpiderSmsService;

@Service
public class SpiderSmsServiceImpl extends BaseServiceImpl<SpiderSms, SpiderSmsSearch> implements SpiderSmsService {

	@Autowired
	private SpiderSmsDao dao;

	@Override
	protected BaseDao<SpiderSms, SpiderSmsSearch> getBaseDao() {
		return dao;
	}
	
}
