package com.aylson.dc.htt.dao.impl;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.HttInitConfigDao;
import com.aylson.dc.htt.po.HttInitConfig;
import com.aylson.dc.htt.search.HttInitConfigSearch;

@Repository
public class HttInitConfigDaoImpl extends BaseDaoImpl<HttInitConfig, HttInitConfigSearch> implements HttInitConfigDao {

}
