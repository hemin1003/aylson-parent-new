package com.aylson.dc.htt.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttWithdrawProveUserLinkDao;
import com.aylson.dc.htt.po.HttWithdrawProveUserLink;
import com.aylson.dc.htt.search.HttWithdrawProveUserLinkSearch;
import com.aylson.dc.htt.service.HttWithdrawProveUserLinkService;

@Service
public class HttWithdrawProveUserLinkServiceImpl  extends BaseServiceImpl<HttWithdrawProveUserLink, HttWithdrawProveUserLinkSearch> implements HttWithdrawProveUserLinkService {
	
	protected static final Logger logger = Logger.getLogger(HttWithdrawProveUserLinkServiceImpl.class);

	@Autowired
	private HttWithdrawProveUserLinkDao httWithdrawProveUserLinkDao;
	
	@Override
	protected BaseDao<HttWithdrawProveUserLink, HttWithdrawProveUserLinkSearch> getBaseDao() {
		return httWithdrawProveUserLinkDao;
	}

}
