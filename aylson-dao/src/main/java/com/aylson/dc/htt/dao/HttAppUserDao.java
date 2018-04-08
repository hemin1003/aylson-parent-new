package com.aylson.dc.htt.dao;

import java.util.List;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.dc.htt.po.HttAppUser;
import com.aylson.dc.htt.search.HttAppUserSearch;
import com.aylson.dc.htt.vo.HttAppUserVo;

public interface HttAppUserDao extends BaseDao<HttAppUser, HttAppUserSearch> {

	public long selectIsInviteCodeExist(String code);

	public List<HttAppUserVo> selectByIdList(List<String> idList);

	// 短信拉活-获取全部用户的手机号码
	public List<String> selectAllRealPhoneNum();
}
