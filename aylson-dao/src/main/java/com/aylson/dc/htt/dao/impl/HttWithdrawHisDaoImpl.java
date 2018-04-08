package com.aylson.dc.htt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aylson.core.frame.dao.impl.BaseDaoImpl;
import com.aylson.dc.htt.dao.HttWithdrawHisDao;
import com.aylson.dc.htt.po.HttWithdrawHis;
import com.aylson.dc.htt.search.HttWithdrawHisSearch;
import com.aylson.dc.htt.vo.HttWithdrawHisVo;

@Repository
public class HttWithdrawHisDaoImpl extends BaseDaoImpl<HttWithdrawHis, HttWithdrawHisSearch> implements HttWithdrawHisDao {

	@Override
	public List<HttWithdrawHisVo> selectByIdList(List<String> idList) {
		return this.sqlSessionTemplate.selectList("httWithdrawHisSelectByIdList", idList);
	}

	@Override
	public Boolean batchUpdateByPhoneNum(List<HttWithdrawHisVo> withdrawHisList) {
		int i = this.sqlSessionTemplate.update("batchUpdateByPhoneNum", withdrawHisList);
		return i > 0 ? true : false;
	}

	@Override
	public List<HttWithdrawHisVo> selectByPhoneNumList(List<String> phoneNumList) {
		return this.sqlSessionTemplate.selectList("httWithdrawHisSelectByPhoneNumList", phoneNumList);
	}

}
