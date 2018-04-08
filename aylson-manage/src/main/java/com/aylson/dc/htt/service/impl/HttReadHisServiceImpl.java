package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttReadHisDao;
import com.aylson.dc.htt.po.HttReadHis;
import com.aylson.dc.htt.search.HttReadHisSearch;
import com.aylson.dc.htt.service.HttReadHisService;

@Service
public class HttReadHisServiceImpl extends BaseServiceImpl<HttReadHis, HttReadHisSearch> implements HttReadHisService {

	@Autowired
	private HttReadHisDao readHisDao;

	@Override
	protected BaseDao<HttReadHis, HttReadHisSearch> getBaseDao() {
		return readHisDao;
	}
}
