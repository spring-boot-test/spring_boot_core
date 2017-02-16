//package com.hubert.core.security.web;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * openId 授权校验    开放资源  Oauth整合
// * @author Hubrt
// *
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class OpenIDLoginConfig extends WebSecurityConfigurerAdapter{
//	/**定义安全策略**/  
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//        .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/", true)
//        .and().logout().logoutUrl("/logout")
//        .and().sessionManagement().maximumSessions(1).expiredUrl("/expired")
//        .and()
//        .and().exceptionHandling().accessDeniedPage("/accessDenied")
//        .and().rememberMe();  
//		
//		//关闭csrf 防止循环定向
//	     http.csrf().disable();
//	     
//	     http.authorizeRequests()//配置安全策略  
//         .antMatchers("/","/hello").permitAll()//定义/请求不需要验证  
//         .anyRequest().authenticated()//其余的所有请求都需要验证  
//         .and()
//         .logout().permitAll()//定义logout不需要验证  
//         .and()  
//         .formLogin();//使用form表单登录  
//	}
//	
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
//	}
//
//
//	/**定义认证用户信息获取来源，密码校验规则等**/ 
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //jdbcAuthentication从数据库中获取，但是默认是以security提供的表结构  
//        //usersByUsernameQuery 指定查询用户SQL  
//        //authoritiesByUsernameQuery 指定查询权限SQL  
//        //auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query).authoritiesByUsernameQuery(query); 
//		
//		
//        //  userDetailsService可从数据库 / http请求 用户数据验证登录   可参考UserService/OpenIDDetailService等
//        //注入userDetailsService，需要实现userDetailsService接口  
//        //auth.userDetailsService(userDetailsService);  
//		
//		//支持LDAP
////		auth.ldapAuthentication()
////		.userDnPatterns("uid={0},ou=people")
////		.groupSearchBase("ou=groups")
////		.contextSource(contextSource()).passwordCompare()
////		.passwordEncoder(new LdapShaPasswordEncoder())
////		.passwordAttribute("userPassword");
//		
//	}
//	
//	
//	
//}
