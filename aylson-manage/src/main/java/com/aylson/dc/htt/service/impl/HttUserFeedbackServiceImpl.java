package com.aylson.dc.htt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.HttUserFeedbackDao;
import com.aylson.dc.htt.po.HttUserFeedback;
import com.aylson.dc.htt.search.HttUserFeedbackSearch;
import com.aylson.dc.htt.service.HttUserFeedbackService;

@Service
public class HttUserFeedbackServiceImpl  extends BaseServiceImpl<HttUserFeedback, HttUserFeedbackSearch> implements HttUserFeedbackService {

	@Autowired
	private HttUserFeedbackDao httUserFeedbackDao;

	@Override
	protected BaseDao<HttUserFeedback, HttUserFeedbackSearch> getBaseDao() {
		return httUserFeedbackDao;
	}
}
