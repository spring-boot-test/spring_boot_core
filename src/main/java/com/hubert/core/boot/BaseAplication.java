package com.hubert.core.boot;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;

import com.hubert.core.utils.IniUtil;

/**
 * Spring Boot适配到Web MVC，Web应用相关Web安全等公用部分的基类。
 * @author Hubrt
 *
 */
public class BaseAplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer{
	
	public BaseAplication() {
	}
	
	/**
	 * 动态加载 messages/exception文件里的消息描述
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages/exception");
		// if true, the key of the message will be displayed if the key is not
		// found, instead of throwing a NoSuchMessageException
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		// # -1 : never reload, 0 always reload
		messageSource.setCacheSeconds(0);
		return messageSource;
	}
	
	
	/**
	 * Url重构   (接口可用于tomcat调优)
	 */
	public void customize(ConfigurableEmbeddedServletContainer container) {
		try {
			IniUtil iniUtil = new IniUtil();
			Properties p = iniUtil.loadFileFromClassPath("application.properties");    //配置文件
			String profile = p.getProperty("spring.profiles.active");
			String contextPath = p.getProperty("server.contextPath");
			// API application need use IP test in the development environment
			Boolean isApiApplication = StringUtils.contains(contextPath, "api"); 
			if (isApiApplication || "sit".equalsIgnoreCase(profile) || "uat".equalsIgnoreCase(profile) || "prod".equalsIgnoreCase(profile)) {
				container.setAddress(InetAddress.getLocalHost());
				System.out.println(InetAddress.getLocalHost().getHostAddress());
				System.out.println(InetAddress.getLocalHost().getHostName());
			} else {
				container.setAddress(InetAddress.getByName("localhost"));
			}
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error"));
			container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
