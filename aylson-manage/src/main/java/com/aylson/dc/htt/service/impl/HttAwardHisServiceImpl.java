package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttAwardHisDao;
import com.aylson.dc.htt.po.HttAwardHis;
import com.aylson.dc.htt.search.HttAwardHisSearch;
import com.aylson.dc.htt.service.HttAwardHisService;

@Service
public class HttAwardHisServiceImpl  extends BaseServiceImpl<HttAwardHis, HttAwardHisSearch> implements HttAwardHisService {

	@Autowired
	private HttAwardHisDao awardHisDao;

	@Override
	protected BaseDao<HttAwardHis, HttAwardHisSearch> getBaseDao() {
		return awardHisDao;
	}
}
