package com.aylson.dc.htt.dao;

import java.util.Map;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.dc.htt.po.BigUser;
import com.aylson.dc.htt.search.BigUserSearch;

public interface BigUserDao extends BaseDao<BigUser, BigUserSearch> {
	public boolean insertTimeHis(Map<Object, Object> map);
}
