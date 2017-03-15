package com.hubert.core.utils;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.cache.support.SimpleCacheManager;

import com.alibaba.fastjson.JSON;
import com.hubert.core.redis.RedisCache;

public class CacheUtils {
	
	private static Logger logger = Logger.getLogger(CacheUtils.class);
	
	private CacheUtils() {
	}
	
	
	public static RedisCache getSharedCache(){
		IniUtil iniUtil = new IniUtil();
		Properties p = null;
		try {
			p = iniUtil.loadFileFromClassPath("core.properties");
		} catch (IOException e) {
			logger.info("读取配置文件失败，请确认！");
			//e.printStackTrace();
		}
		String redisName = p.getProperty("redis.cache.name");
		SimpleCacheManager cacheManager = (SimpleCacheManager) SpringContextUtils.getBean("cacheManager");
		RedisCache sharedCache = (RedisCache) cacheManager.getCache(redisName);
		return sharedCache;
	}
	
	
	public static String getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(name)) {
				return cookies[i].getValue();
			}
		}
		return null;
	}

	public static void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			return;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				cookie.setValue(null);
				cookie.setMaxAge(0);// 立即销毁cookie
				response.addCookie(cookie);
				break;
			}
		}
	}
}
