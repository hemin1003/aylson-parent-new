package com.aylson.dc.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.HttUserInfoDao;
import com.aylson.dc.htt.po.HttUserInfo;
import com.aylson.dc.htt.search.HttUserInfoSearch;

@Repository
public class HttUserInfoDaoImpl extends BaseDaoImpl<HttUserInfo, HttUserInfoSearch> implements HttUserInfoDao {


	@Override
	public List<HttUserInfo> selectNumsOfTag(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectList(this.getSqlName("selectNumsOfTag"), params);
	}
}
