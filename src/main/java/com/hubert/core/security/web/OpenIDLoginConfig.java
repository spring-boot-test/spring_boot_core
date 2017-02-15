package com.hubert.core.security.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * openId 授权校验    开放资源  Oauth整合
 * @author Hubrt
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OpenIDLoginConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/", true)
        .and().logout().logoutUrl("/logout")
        .and().sessionManagement().maximumSessions(1).expiredUrl("/expired")
        .and()
        .and().exceptionHandling().accessDeniedPage("/accessDenied")
        .and().rememberMe();  
		
		//关闭csrf 防止循环定向
	     http.csrf().disable();
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		super.configure(auth);
	}
	
	
	
}
