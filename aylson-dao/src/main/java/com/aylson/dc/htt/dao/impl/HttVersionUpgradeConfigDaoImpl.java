package com.aylson.dc.htt.dao.impl;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.HttVersionUpgradeConfigDao;
import com.aylson.dc.htt.po.HttVersionUpgradeConfig;
import com.aylson.dc.htt.search.HttVersionUpgradeConfigSearch;

@Repository
public class HttVersionUpgradeConfigDaoImpl extends BaseDaoImpl<HttVersionUpgradeConfig, HttVersionUpgradeConfigSearch> implements HttVersionUpgradeConfigDao {

}
