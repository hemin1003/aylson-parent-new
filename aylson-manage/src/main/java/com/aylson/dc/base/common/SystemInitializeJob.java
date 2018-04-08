package com.aylson.dc.base.common;

import com.aylson.core.utils.SpringBeanManager;
import com.aylson.utils.StringUtil;

/**
 * 系统初始化
 * 
 */
public class SystemInitializeJob {
	
	private static boolean hadInit = true;//是否需要初始化

	public static void start() {
		if (hadInit) {
			hadInit = false;
			initCurrentMaxBillSN();
		}
	}
	
	private static void initCurrentMaxBillSN() {
		
	}
	
	
}
