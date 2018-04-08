package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttRateSetDao;
import com.aylson.dc.htt.po.HttRateSet;
import com.aylson.dc.htt.search.HttRateSetSearch;
import com.aylson.dc.htt.service.HttRateSetService;

@Service
public class HttRateSetServiceImpl extends BaseServiceImpl<HttRateSet, HttRateSetSearch> implements HttRateSetService {

	@Autowired
	private HttRateSetDao rateSetDao;

	@Override
	protected BaseDao<HttRateSet, HttRateSetSearch> getBaseDao() {
		return rateSetDao;
	}
}
