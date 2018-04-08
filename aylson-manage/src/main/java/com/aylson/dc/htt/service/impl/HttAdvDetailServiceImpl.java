package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttAdvDetailDao;
import com.aylson.dc.htt.po.HttAdvDetail;
import com.aylson.dc.htt.search.HttAdvDetailSearch;
import com.aylson.dc.htt.service.HttAdvDetailService;

@Service
public class HttAdvDetailServiceImpl  extends BaseServiceImpl<HttAdvDetail, HttAdvDetailSearch> implements HttAdvDetailService {

	@Autowired
	private HttAdvDetailDao advDetailDao;

	@Override
	protected BaseDao<HttAdvDetail, HttAdvDetailSearch> getBaseDao() {
		return advDetailDao;
	}
}
