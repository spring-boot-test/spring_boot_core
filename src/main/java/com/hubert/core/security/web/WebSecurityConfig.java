package com.hubert.core.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.hubert.core.security.service.UserService;


/**
 * 授权校验 
 * 通过@EnableWebSecurity注解开启Spring Security的功能
 * 继承WebSecurityConfigurerAdapter，并重写它的方法来设置一些web安全的细节
 * @author Hubrt
 *
 */
@Configuration
//@EnableWebSecurity: 禁用Boot的默认Security配置，配合@Configuration启用自定义配置
//（需要扩展WebSecurityConfigurerAdapter）
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true): 启用Security注解，
//例如最常用的@PreAuthorize
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private UserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		//通过authorizeRequests()定义哪些URL需要被保护、哪些不需要被保护。例如代码指定了/和/login不需要任何认证就可以访问，其他的路径都必须通过身份验证。
		//通过formLogin()定义当需要用户登录时候，转到的登录页面。
		 http.authorizeRequests().antMatchers("/login","/").permitAll().anyRequest().authenticated()
         .and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();
		 //.and().logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/login");  //退出登录可以在页面上用默认param.logout,也可以重定向到新页面
		 //关闭csrf 防止循环定向
	     http.csrf().disable();
	}
	/**
	 * configureGlobal(AuthenticationManagerBuilder auth)方法，在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER。
	 * 可用UserDetailsService Spring通过UserDetailsService方便开发者定义自己的身份验证逻辑。UserDetailsService的职责非常简单：给一个用户名，返回一个UserDetails 实现，UserDetails会回答这些问题：用户的合法性、用户名/密码、用户的权利
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER。
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		//userDetailsService 开发者定义自己的身份验证逻辑
		auth.userDetailsService(this.userService);
	}
	
	

}
