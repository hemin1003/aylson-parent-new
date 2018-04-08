package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttAdvListDao;
import com.aylson.dc.htt.po.HttAdvList;
import com.aylson.dc.htt.search.HttAdvListSearch;
import com.aylson.dc.htt.service.HttAdvListService;

@Service
public class HttAdvListServiceImpl  extends BaseServiceImpl<HttAdvList, HttAdvListSearch> implements HttAdvListService {

	@Autowired
	private HttAdvListDao advListDao;

	@Override
	protected BaseDao<HttAdvList, HttAdvListSearch> getBaseDao() {
		return advListDao;
	}
}
