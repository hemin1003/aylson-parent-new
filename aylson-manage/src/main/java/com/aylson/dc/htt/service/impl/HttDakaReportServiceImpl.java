package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttDakaReportDao;
import com.aylson.dc.htt.po.HttDakaReport;
import com.aylson.dc.htt.search.HttDakaReportSearch;
import com.aylson.dc.htt.service.HttDakaReportService;

@Service
public class HttDakaReportServiceImpl extends BaseServiceImpl<HttDakaReport, HttDakaReportSearch> implements HttDakaReportService {

	@Autowired
	private HttDakaReportDao shareDakaReportDao;

	@Override
	protected BaseDao<HttDakaReport, HttDakaReportSearch> getBaseDao() {
		return shareDakaReportDao;
	}
	
}
