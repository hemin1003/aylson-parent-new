package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttBalanceHisDao;
import com.aylson.dc.htt.po.HttBalanceHis;
import com.aylson.dc.htt.search.HttBalanceHisSearch;
import com.aylson.dc.htt.service.HttBalanceHisService;

@Service
public class HttBalanceHisServiceImpl  extends BaseServiceImpl<HttBalanceHis, HttBalanceHisSearch> implements HttBalanceHisService {

	@Autowired
	private HttBalanceHisDao balanceHisDao;

	@Override
	protected BaseDao<HttBalanceHis, HttBalanceHisSearch> getBaseDao() {
		return balanceHisDao;
	}
}
