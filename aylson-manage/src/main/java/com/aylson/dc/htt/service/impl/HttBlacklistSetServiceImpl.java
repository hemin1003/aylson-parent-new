package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttBlacklistSetDao;
import com.aylson.dc.htt.po.HttBlacklistSet;
import com.aylson.dc.htt.search.HttBlacklistSetSearch;
import com.aylson.dc.htt.service.HttBlacklistSetService;

@Service
public class HttBlacklistSetServiceImpl extends BaseServiceImpl<HttBlacklistSet, HttBlacklistSetSearch> implements HttBlacklistSetService {

	@Autowired
	private HttBlacklistSetDao shareHttBlacklistSetDao;

	@Override
	protected BaseDao<HttBlacklistSet, HttBlacklistSetSearch> getBaseDao() {
		return shareHttBlacklistSetDao;
	}
}
