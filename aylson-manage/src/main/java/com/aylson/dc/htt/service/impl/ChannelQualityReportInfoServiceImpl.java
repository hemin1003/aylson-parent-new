package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.ChannelQualityReportInfoDao;
import com.aylson.dc.htt.po.ChannelQualityReportInfo;
import com.aylson.dc.htt.search.ChannelQualityReportInfoSearch;
import com.aylson.dc.htt.service.ChannelQualityReportInfoService;

@Service
public class ChannelQualityReportInfoServiceImpl extends BaseServiceImpl<ChannelQualityReportInfo, ChannelQualityReportInfoSearch> implements ChannelQualityReportInfoService {

	@Autowired
	private ChannelQualityReportInfoDao shareChannelQualityReportInfoDao;

	@Override
	protected BaseDao<ChannelQualityReportInfo, ChannelQualityReportInfoSearch> getBaseDao() {
		return shareChannelQualityReportInfoDao;
	}
}
