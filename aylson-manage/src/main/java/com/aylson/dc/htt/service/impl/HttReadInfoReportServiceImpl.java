package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttReadInfoReportDao;
import com.aylson.dc.htt.po.HttReadInfoReport;
import com.aylson.dc.htt.search.HttReadInfoReportSearch;
import com.aylson.dc.htt.service.HttReadInfoReportService;

@Service
public class HttReadInfoReportServiceImpl extends BaseServiceImpl<HttReadInfoReport, HttReadInfoReportSearch>
		implements HttReadInfoReportService {

	@Autowired
	private HttReadInfoReportDao shareHttReadInfoReportDao;

	@Override
	protected BaseDao<HttReadInfoReport, HttReadInfoReportSearch> getBaseDao() {
		return shareHttReadInfoReportDao;
	}
}
