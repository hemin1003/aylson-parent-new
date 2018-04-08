package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttAwardCheckinHisDao;
import com.aylson.dc.htt.po.HttAwardCheckinHis;
import com.aylson.dc.htt.search.HttAwardCheckinHisSearch;
import com.aylson.dc.htt.service.HttAwardCheckinHisService;

@Service
public class HttAwardCheckinHisServiceImpl  extends BaseServiceImpl<HttAwardCheckinHis, HttAwardCheckinHisSearch> implements HttAwardCheckinHisService {

	@Autowired
	private HttAwardCheckinHisDao awardCheckinHisDao;

	@Override
	protected BaseDao<HttAwardCheckinHis, HttAwardCheckinHisSearch> getBaseDao() {
		return awardCheckinHisDao;
	}
}
