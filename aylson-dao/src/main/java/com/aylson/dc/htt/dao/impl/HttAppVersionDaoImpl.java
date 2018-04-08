package com.aylson.dc.htt.dao.impl;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.HttAppVersionDao;
import com.aylson.dc.htt.po.HttAppVersion;
import com.aylson.dc.htt.search.HttAppVersionSearch;

@Repository
public class HttAppVersionDaoImpl extends BaseDaoImpl<HttAppVersion, HttAppVersionSearch> implements HttAppVersionDao {

}
