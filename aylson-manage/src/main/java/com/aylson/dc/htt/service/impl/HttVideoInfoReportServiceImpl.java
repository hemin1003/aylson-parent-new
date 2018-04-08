package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttVideoInfoReportDao;
import com.aylson.dc.htt.po.HttVideoInfoReport;
import com.aylson.dc.htt.search.HttVideoInfoReportSearch;
import com.aylson.dc.htt.service.HttVideoInfoReportService;

@Service
public class HttVideoInfoReportServiceImpl extends BaseServiceImpl<HttVideoInfoReport, HttVideoInfoReportSearch>
		implements HttVideoInfoReportService {

	@Autowired
	private HttVideoInfoReportDao shareHttVideoInfoReportDao;

	@Override
	protected BaseDao<HttVideoInfoReport, HttVideoInfoReportSearch> getBaseDao() {
		return shareHttVideoInfoReportDao;
	}
}
