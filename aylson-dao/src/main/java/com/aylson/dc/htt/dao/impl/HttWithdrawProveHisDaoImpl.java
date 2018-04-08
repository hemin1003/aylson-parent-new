package com.aylson.dc.htt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.HttWithdrawProveHisDao;
import com.aylson.dc.htt.po.HttWithdrawProveHis;
import com.aylson.dc.htt.search.HttWithdrawProveHisSearch;

@Repository
public class HttWithdrawProveHisDaoImpl extends BaseDaoImpl<HttWithdrawProveHis, HttWithdrawProveHisSearch> implements HttWithdrawProveHisDao {

	@Override
	public List<String> getIdList(HttWithdrawProveHisSearch httWithdrawProveHisSearch) {
		return this.sqlSessionTemplate.selectList("selectIdList",httWithdrawProveHisSearch );
	}

}
