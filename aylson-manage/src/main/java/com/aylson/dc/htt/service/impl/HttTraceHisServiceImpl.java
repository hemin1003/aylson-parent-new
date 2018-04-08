package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttTraceHisDao;
import com.aylson.dc.htt.po.HttTraceHis;
import com.aylson.dc.htt.search.HttTraceHisSearch;
import com.aylson.dc.htt.service.HttTraceHisService;

@Service
public class HttTraceHisServiceImpl extends BaseServiceImpl<HttTraceHis, HttTraceHisSearch> implements HttTraceHisService {

	@Autowired
	private HttTraceHisDao shareTraceHisDao;

	@Override
	protected BaseDao<HttTraceHis, HttTraceHisSearch> getBaseDao() {
		return shareTraceHisDao;
	}
}
