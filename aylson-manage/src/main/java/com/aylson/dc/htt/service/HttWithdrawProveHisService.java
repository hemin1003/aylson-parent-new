package com.aylson.dc.htt.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.aylson.core.frame.service.BaseService;
import com.aylson.dc.htt.po.HttWithdrawProveHis;
import com.aylson.dc.htt.search.HttWithdrawProveHisSearch;
import com.aylson.dc.htt.vo.HttWithdrawHisVo;
import com.aylson.dc.sys.common.SessionInfo;

import net.sf.json.JSONObject;

public interface HttWithdrawProveHisService extends BaseService<HttWithdrawProveHis, HttWithdrawProveHisSearch> {

	@Transactional
	public JSONObject updateHttWithdrawProveHisList(List<HttWithdrawHisVo> httWithdrawHisVoList, String curPhoneNum,
			String status, SessionInfo sessionInfo, String id);
	
	public List<String> getIdList(HttWithdrawProveHisSearch httWithdrawProveHisSearch);

}
