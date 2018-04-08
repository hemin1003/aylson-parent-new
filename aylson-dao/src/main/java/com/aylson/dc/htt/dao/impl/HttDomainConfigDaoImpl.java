package com.aylson.dc.htt.dao.impl;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.HttDomainConfigDao;
import com.aylson.dc.htt.po.HttDomainConfig;
import com.aylson.dc.htt.search.HttDomainConfigSearch;

@Repository
public class HttDomainConfigDaoImpl extends BaseDaoImpl<HttDomainConfig, HttDomainConfigSearch> implements HttDomainConfigDao {

}
