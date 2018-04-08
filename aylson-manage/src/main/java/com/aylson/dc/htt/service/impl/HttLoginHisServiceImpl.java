package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttLoginHisDao;
import com.aylson.dc.htt.po.HttLoginHis;
import com.aylson.dc.htt.search.HttLoginHisSearch;
import com.aylson.dc.htt.service.HttLoginHisService;

@Service
public class HttLoginHisServiceImpl extends BaseServiceImpl<HttLoginHis, HttLoginHisSearch> implements HttLoginHisService {

	@Autowired
	private HttLoginHisDao shareLoginHisDao;

	@Override
	protected BaseDao<HttLoginHis, HttLoginHisSearch> getBaseDao() {
		return shareLoginHisDao;
	}
}
