package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttAwardHisChildDao;
import com.aylson.dc.htt.po.HttAwardHisChild;
import com.aylson.dc.htt.search.HttAwardHisChildSearch;
import com.aylson.dc.htt.service.HttAwardHisChildService;

@Service
public class HttAwardHisChildServiceImpl  extends BaseServiceImpl<HttAwardHisChild, HttAwardHisChildSearch> implements HttAwardHisChildService {

	@Autowired
	private HttAwardHisChildDao awardHisChildChildDao;

	@Override
	protected BaseDao<HttAwardHisChild, HttAwardHisChildSearch> getBaseDao() {
		return awardHisChildChildDao;
	}
}
