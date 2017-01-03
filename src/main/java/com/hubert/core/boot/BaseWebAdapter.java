package com.hubert.core.boot;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.druid.support.http.StatViewServlet;

public class BaseWebAdapter extends WebMvcConfigurerAdapter{
	
	@Bean   //注册druid
	@Order //使用注解方式使bean的加载顺序得到控制(value=1)值越小，越先被加载。
	public ServletRegistrationBean statViewServlet() {    
		StatViewServlet servlet = new StatViewServlet();
		ServletRegistrationBean bean = new ServletRegistrationBean(servlet, "/druid/*");//context.embedded.ServletRegistrationBean -- 1.3.5未失效    1.4.2失效
		return bean;
	}
	
	//开启CORS跨域支持  
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
	}
	
	//解决分页问题
//	@Override
//	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//		//super.extendMessageConverters(converters);
//		PageHttpMessageConverter pageHttpMessageConverter = new PageHttpMessageConverter();
//		List<HttpMessageConverter<?>> newConverters = new ArrayList<HttpMessageConverter<?>>(converters.size() + 1);
//		newConverters.add(pageHttpMessageConverter);
//		newConverters.addAll(converters);
//        converters.clear();
//		converters.addAll(newConverters);
//	}
	
	
}
