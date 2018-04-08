package com.aylson.dc.htt.dao;

import java.util.List;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.dc.htt.po.HttWithdrawProveHis;
import com.aylson.dc.htt.search.HttWithdrawProveHisSearch;

public interface HttWithdrawProveHisDao extends BaseDao<HttWithdrawProveHis, HttWithdrawProveHisSearch> {
	
	public List<String> getIdList(HttWithdrawProveHisSearch httWithdrawProveHisSearch);
	
}
