package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttTimeHisDao;
import com.aylson.dc.htt.po.HttTimeHis;
import com.aylson.dc.htt.search.HttTimeHisSearch;
import com.aylson.dc.htt.service.HttTimeHisService;

@Service
public class HttTimeHisServiceImpl extends BaseServiceImpl<HttTimeHis, HttTimeHisSearch> implements HttTimeHisService {

	@Autowired
	private HttTimeHisDao shareTimeHisDao;

	@Override
	protected BaseDao<HttTimeHis, HttTimeHisSearch> getBaseDao() {
		return shareTimeHisDao;
	}
}
