package com.aylson.dc.htt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.HttAppUserDao;
import com.aylson.dc.htt.po.HttAppUser;
import com.aylson.dc.htt.search.HttAppUserSearch;
import com.aylson.dc.htt.vo.HttAppUserVo;

@Repository
public class HttAppUserDaoImpl extends BaseDaoImpl<HttAppUser, HttAppUserSearch> implements HttAppUserDao {
	@Override
	public long selectIsInviteCodeExist(String code) {
		return sqlSessionTemplate.selectOne(getSqlName("selectIsInviteCodeExist"), code);
	}

	@Override
	public List<HttAppUserVo> selectByIdList(List<String> idList) {
		return this.sqlSessionTemplate.selectList("httAppUserSelectByIdList", idList);
	}

	@Override
	public List<String> selectAllRealPhoneNum() {
		return this.sqlSessionTemplate.selectList("selectAllRealPhoneNum");
	}
}
