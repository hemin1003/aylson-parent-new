package com.aylson.dc.htt.service;

import java.util.List;

import com.aylson.core.frame.domain.Result;
import com.aylson.core.frame.service.BaseService;
import com.aylson.dc.htt.po.HttAppUser;
import com.aylson.dc.htt.search.HttAppUserSearch;
import com.aylson.dc.htt.vo.HttAppUserVo;

public interface HttAppUserService  extends BaseService<HttAppUser, HttAppUserSearch> {

	/**
	 * 黑名单导入-处理黑名单记录，同时处理相应的提现记录，置为审核失败
	 * @param appUserVo
	 * @return
	 */
	public Result importOfProcessBlackList(List<List<Object>> listob);
	
	/**
	 * 短信拉活用户
	 */
	public Result sendSms(List<String> phoneNumList);
	
	public List<HttAppUserVo> selectByIdList(List<String> idList) ;
	

	/**
	 * 用户黑名单调整（当取消黑名单时，则提现记录为‘审核失败’的记录需要回退给用户）
	 * @param appUserVo
	 * @return
	 */
	public boolean isBlackList(HttAppUserVo appUserVo);
	
	/**
	 * 短信拉活-获取全部用户的手机号码
	 * @return
	 */
	public List<String> selectAllRealPhoneNum();
	
}
