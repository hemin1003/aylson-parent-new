package com.aylson.dc.htt.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.aylson.core.frame.domain.Result;
import com.aylson.core.frame.service.BaseService;
import com.aylson.dc.htt.po.HttWithdrawHis;
import com.aylson.dc.htt.search.HttWithdrawHisSearch;
import com.aylson.dc.htt.vo.HttWithdrawHisVo;

public interface HttWithdrawHisService  extends BaseService<HttWithdrawHis, HttWithdrawHisSearch> {

	/**
	 * 更新提现信息，如果失败，则回退用户余额
	 */
	@Transactional
	public Result updateWithdrawHisInfo(HttWithdrawHisVo httWithdrawHisVo);
	
	public List<HttWithdrawHisVo> selectByIdList(List<String> idList) ;
	
	public List<HttWithdrawHisVo> selectByPhoneNumList(List<String> phoneNumList) ;
	
	public Boolean batchUpdateByPhoneNum(List<HttWithdrawHisVo> withdrawHisList);
	
}
