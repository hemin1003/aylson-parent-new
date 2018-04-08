package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttInviteUserHisDao;
import com.aylson.dc.htt.po.HttInviteUserHis;
import com.aylson.dc.htt.search.HttInviteUserHisSearch;
import com.aylson.dc.htt.service.HttInviteUserHisService;

@Service
public class HttInviteUserHisServiceImpl extends BaseServiceImpl<HttInviteUserHis, HttInviteUserHisSearch> implements HttInviteUserHisService {

	@Autowired
	private HttInviteUserHisDao shareUserHisDao;

	@Override
	protected BaseDao<HttInviteUserHis, HttInviteUserHisSearch> getBaseDao() {
		return shareUserHisDao;
	}
}
