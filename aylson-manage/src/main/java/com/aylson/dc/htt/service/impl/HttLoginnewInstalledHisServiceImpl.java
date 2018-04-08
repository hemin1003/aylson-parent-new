package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttLoginnewInstalledHisDao;
import com.aylson.dc.htt.po.HttLoginnewInstalledHis;
import com.aylson.dc.htt.search.HttLoginnewInstalledHisSearch;
import com.aylson.dc.htt.service.HttLoginnewInstalledHisService;

@Service
public class HttLoginnewInstalledHisServiceImpl extends BaseServiceImpl<HttLoginnewInstalledHis, HttLoginnewInstalledHisSearch> implements HttLoginnewInstalledHisService {

	@Autowired
	private HttLoginnewInstalledHisDao shareLoginnewInstalledHisDao;

	@Override
	protected BaseDao<HttLoginnewInstalledHis, HttLoginnewInstalledHisSearch> getBaseDao() {
		return shareLoginnewInstalledHisDao;
	}
	
}
