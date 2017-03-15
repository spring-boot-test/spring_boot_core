//package com.hubert.core.redis;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisShardInfo;
//import redis.clients.jedis.ShardedJedisPool;
//   //需要spring-boot-starter-redis
//@Configuration
//@ComponentScan
//public class JedisConfiguration {
//	
//	 @Autowired
//	 RedisConfig redisConfig;
//	 
//	 /**
//	  * jedisPool
//	  * @return
//	  */
//	 @Bean
//	 public ShardedJedisPool convertJedisPool() {
//		 JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//	     jedisPoolConfig.setMaxTotal(redisConfig.getMaxTotal());
//	     jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
//	     jedisPoolConfig.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
//	     jedisPoolConfig.setTestOnBorrow(redisConfig.getTestOnBorrow());
//	     List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>();
//	     jedisShardInfoList.add(new JedisShardInfo(redisConfig.getUrl()));
//	     return new ShardedJedisPool(jedisPoolConfig, jedisShardInfoList);
//	 }
//	 
//}
