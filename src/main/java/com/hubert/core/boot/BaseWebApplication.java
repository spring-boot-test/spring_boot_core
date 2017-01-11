package com.hubert.core.boot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.support.StandardServletEnvironment;

/**
 * Spring Boot Web应用启动类的基类
 * 
 * @author Hubrt
 *
 */
public class BaseWebApplication extends BaseAplication {
	
	public void run(String[] args) {
		HashMap<String, Object> props = new HashMap<String, Object>();
		props.put("spring.thymeleaf.cache", Boolean.FALSE);
		props.put("security.basic.enabled", Boolean.FALSE);
		props.put("security.enable-csrf", Boolean.FALSE);
		props.put("logging.level.org.springframework.security", "INFO");
		props.put("server.error.whitelabel.enabled", Boolean.FALSE);
		ConfigurableEnvironment environment = new StandardServletEnvironment();
		SpringApplication app = new SpringApplication(this.getClass());
		app.setBannerMode(Mode.OFF);
		app.setWebEnvironment(Boolean.TRUE);
		app.setDefaultProperties(props);
		app.setEnvironment(environment);
		Set<Object> sources = new HashSet<Object>();
//		sources.add("classpath:spring/spring-context-ds.xml");
//		sources.add("classpath:spring/spring-context-bpm.xml");
//		sources.add("classpath:spring/spring-context-cache.xml");
//		sources.add("classpath:spring/spring-context-shiro.xml");
//		sources.add("classpath:spring/spring-context-task.xml");
		// sources.add("classpath:spring/spring-context-druid.xml");//未来可以启用监控，调优连接池
		app.setSources(sources);
		app.run(args);
	}
}
