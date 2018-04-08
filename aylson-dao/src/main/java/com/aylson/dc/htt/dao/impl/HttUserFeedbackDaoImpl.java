package com.aylson.dc.htt.dao.impl;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.HttUserFeedbackDao;
import com.aylson.dc.htt.po.HttUserFeedback;
import com.aylson.dc.htt.search.HttUserFeedbackSearch;

@Repository
public class HttUserFeedbackDaoImpl extends BaseDaoImpl<HttUserFeedback, HttUserFeedbackSearch> implements HttUserFeedbackDao {

}
