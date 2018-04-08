package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttIncomeSetDao;
import com.aylson.dc.htt.po.HttIncomeSet;
import com.aylson.dc.htt.search.HttIncomeSetSearch;
import com.aylson.dc.htt.service.HttIncomeSetService;

@Service
public class HttIncomeSetServiceImpl  extends BaseServiceImpl<HttIncomeSet, HttIncomeSetSearch> implements HttIncomeSetService {

	@Autowired
	private HttIncomeSetDao httIncomeSetDao;

	@Override
	protected BaseDao<HttIncomeSet, HttIncomeSetSearch> getBaseDao() {
		return httIncomeSetDao;
	}
}
