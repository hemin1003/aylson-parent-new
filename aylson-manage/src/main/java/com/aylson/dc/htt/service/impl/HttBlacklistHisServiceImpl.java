package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttBlacklistHisDao;
import com.aylson.dc.htt.po.HttBlacklistHis;
import com.aylson.dc.htt.search.HttBlacklistHisSearch;
import com.aylson.dc.htt.service.HttBlacklistHisService;

@Service
public class HttBlacklistHisServiceImpl extends BaseServiceImpl<HttBlacklistHis, HttBlacklistHisSearch>
		implements HttBlacklistHisService {

	@Autowired
	private HttBlacklistHisDao shareHttBlacklistHisDao;

	@Override
	protected BaseDao<HttBlacklistHis, HttBlacklistHisSearch> getBaseDao() {
		return shareHttBlacklistHisDao;
	}
}
