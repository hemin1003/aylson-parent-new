package com.aylson.dc.htt.dao.impl;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.HttAppConfigDao;
import com.aylson.dc.htt.po.HttAppConfig;
import com.aylson.dc.htt.search.HttAppConfigSearch;

@Repository
public class HttAppConfigDaoImpl extends BaseDaoImpl<HttAppConfig, HttAppConfigSearch> implements HttAppConfigDao {

}
