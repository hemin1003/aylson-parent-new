package com.aylson.dc.htt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aylson.core.frame.dao.BaseDao;
import com.aylson.core.frame.service.impl.BaseServiceImpl;
import com.aylson.dc.htt.dao.BigUserDao;
import com.aylson.dc.htt.dao.HttAppUserDao;
import com.aylson.dc.htt.po.BigUser;
import com.aylson.dc.htt.search.BigUserSearch;
import com.aylson.dc.htt.service.BigUserService;
import com.aylson.dc.htt.vo.BigUserVo;
import com.aylson.dc.htt.vo.HttAppUserVo;
import com.aylson.utils.DateUtil2;
import com.aylson.utils.MD5Util;
import com.aylson.utils.RandomNumberGenerator;
import com.aylson.utils.UUIDUtils;

@Service
public class BigUserServiceImpl  extends BaseServiceImpl<BigUser, BigUserSearch> implements BigUserService {
	
	protected static final Logger logger = Logger.getLogger(BigUserServiceImpl.class);

	@Autowired
	private HttAppUserDao httAppUserDao;
	@Autowired
	private BigUserDao bigUserDao;

	@Override
	protected BaseDao<BigUser, BigUserSearch> getBaseDao() {
		return bigUserDao;
	}

	@Override
	@Transactional
	public int addUser(BigUserVo bigUserVo, HttpServletRequest request) {
		BigUserVo bigUserVo2 = this.bigUserDao.selectById(bigUserVo.getPhoneNum());
		if(bigUserVo2 != null) {
			logger.info("用户已存在，不能重复注册。");
			return 1;
		}
		String userPwd = MD5Util.encodeByMD5(bigUserVo.getUserPwd());
		//1. 注册用户
		HttAppUserVo appUserVo = new HttAppUserVo();
		appUserVo.setPhoneNum(bigUserVo.getPhoneNum());
		appUserVo.setRealPhoneNum(bigUserVo.getPhoneNum());		
		appUserVo.setUserPwd(userPwd);
		appUserVo.setGold("0");
		appUserVo.setBalance("0.00");
		String cTime = DateUtil2.getCurrentLongDateTime();
		appUserVo.setRegisterDate(cTime);
		appUserVo.setLastLoginDate(cTime);
		appUserVo.setUpdateDate(cTime);
		appUserVo.setIsBindPhoneNum(1);
		appUserVo.setIsDaka(1);
		//生成邀请码
		String ownInviteCode = "";
		//五次生成机会，否则置空
		for (int i = 0; i < 5; i++) {
			ownInviteCode = RandomNumberGenerator.generateNumber();
			long count = this.httAppUserDao.selectIsInviteCodeExist(ownInviteCode);
			if(count == 0) {
				break;
			}
		}
		if(ownInviteCode.equals("")) {
			logger.info("邀请码没有生成成功，不能注册，请重试。");
			return 2;
		}
		appUserVo.setInviteCode(ownInviteCode);
		bigUserVo.setInviteCode(ownInviteCode);
		bigUserVo.setCreateDate(cTime);
		bigUserVo.setUpdateDate(cTime);
		boolean flag = this.httAppUserDao.insert(appUserVo);
		boolean flag1 = this.bigUserDao.insert(bigUserVo);
		if(flag && flag1) {
			//增加一条时段记录，减掉配置时间，以便于第一次获得时段奖励
			Map<Object, Object> map = new HashMap<>();
			map.put("id", UUIDUtils.create());
			map.put("phoneNum", bigUserVo.getPhoneNum());
			map.put("createDate", DateUtil2.formatLongDate(DateUtils.addHours(new Date(), -2)));
			map.put("updateDate", cTime);
			this.bigUserDao.insertTimeHis(map);
			return 0;
		}
		return 4;
	}
}
