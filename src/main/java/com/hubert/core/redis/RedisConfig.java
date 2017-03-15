//package com.hubert.core.redis;
//
//import java.io.Serializable;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
// //需要spring-boot-starter-redis
//@Component
//@ConfigurationProperties(prefix = "redisConfig")
//public class RedisConfig implements Serializable{
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -3459089268849620128L;
//	
//	private String url;
//    private Integer maxTotal;
//    private Integer maxIdle;
//    private Long maxWaitMillis;
//    private Boolean testOnBorrow;
//	public String getUrl() {
//		return url;
//	}
//	public void setUrl(String url) {
//		this.url = url;
//	}
//	public Integer getMaxTotal() {
//		return maxTotal;
//	}
//	public void setMaxTotal(Integer maxTotal) {
//		this.maxTotal = maxTotal;
//	}
//	public Integer getMaxIdle() {
//		return maxIdle;
//	}
//	public void setMaxIdle(Integer maxIdle) {
//		this.maxIdle = maxIdle;
//	}
//	public Long getMaxWaitMillis() {
//		return maxWaitMillis;
//	}
//	public void setMaxWaitMillis(Long maxWaitMillis) {
//		this.maxWaitMillis = maxWaitMillis;
//	}
//	public Boolean getTestOnBorrow() {
//		return testOnBorrow;
//	}
//	public void setTestOnBorrow(Boolean testOnBorrow) {
//		this.testOnBorrow = testOnBorrow;
//	}
//    
//    
//	
//}
