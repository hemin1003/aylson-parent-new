package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttStudentConfigDao;
import com.aylson.dc.htt.po.HttStudentConfig;
import com.aylson.dc.htt.search.HttStudentConfigSearch;
import com.aylson.dc.htt.service.HttStudentConfigService;

@Service
public class HttStudentConfigServiceImpl extends BaseServiceImpl<HttStudentConfig, HttStudentConfigSearch> implements HttStudentConfigService {

	@Autowired
	private HttStudentConfigDao studentConfigDao;

	@Override
	protected BaseDao<HttStudentConfig, HttStudentConfigSearch> getBaseDao() {
		return studentConfigDao;
	}
}
