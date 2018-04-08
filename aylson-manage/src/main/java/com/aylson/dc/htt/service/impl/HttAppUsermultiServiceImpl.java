package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttAppUsermultiDao;
import com.aylson.dc.htt.po.HttAppUsermulti;
import com.aylson.dc.htt.search.HttAppUsermultiSearch;
import com.aylson.dc.htt.service.HttAppUsermultiService;

@Service
public class HttAppUsermultiServiceImpl extends BaseServiceImpl<HttAppUsermulti, HttAppUsermultiSearch>
		implements HttAppUsermultiService {

	@Autowired
	private HttAppUsermultiDao shareHttAppUsermultiDao;

	@Override
	protected BaseDao<HttAppUsermulti, HttAppUsermultiSearch> getBaseDao() {
		return shareHttAppUsermultiDao;
	}
	
}
