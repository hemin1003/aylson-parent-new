package com.aylson.dc.htt.dao;

import java.util.List;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.dc.htt.po.HttWithdrawHis;
import com.aylson.dc.htt.search.HttWithdrawHisSearch;
import com.aylson.dc.htt.vo.HttWithdrawHisVo;

public interface HttWithdrawHisDao extends BaseDao<HttWithdrawHis, HttWithdrawHisSearch> {

	public List<HttWithdrawHisVo> selectByIdList(List<String> idList);
	public List<HttWithdrawHisVo> selectByPhoneNumList(List<String> phoneNumList);
	
	public Boolean batchUpdateByPhoneNum(List<HttWithdrawHisVo> withdrawHisList);
	
}
