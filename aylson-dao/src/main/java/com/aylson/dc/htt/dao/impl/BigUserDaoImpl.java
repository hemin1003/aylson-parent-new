package com.aylson.dc.htt.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.BigUserDao;
import com.aylson.dc.htt.po.BigUser;
import com.aylson.dc.htt.search.BigUserSearch;

@Repository
public class BigUserDaoImpl extends BaseDaoImpl<BigUser, BigUserSearch> implements BigUserDao {

	@Override
	@Transactional
	public boolean insertTimeHis(Map<Object, Object> map) {
		return sqlSessionTemplate.insert(getSqlName("insertTimeHis"), map) == 0 ? false : true;
	}

}
