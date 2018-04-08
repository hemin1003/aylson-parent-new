package com.aylson.dc.ytt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.ytt.dao.SpiderEmailDao;
import com.aylson.dc.ytt.po.SpiderEmail;
import com.aylson.dc.ytt.search.SpiderEmailSearch;
import com.aylson.dc.ytt.service.SpiderEmailService;

@Service
public class SpiderEmailServiceImpl extends BaseServiceImpl<SpiderEmail, SpiderEmailSearch> implements SpiderEmailService {

	@Autowired
	private SpiderEmailDao dao;

	@Override
	protected BaseDao<SpiderEmail, SpiderEmailSearch> getBaseDao() {
		return dao;
	}
	
}
