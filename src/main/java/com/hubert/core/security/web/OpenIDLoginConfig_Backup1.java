//package com.hubert.core.security.web;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.openid.OpenIDAuthenticationToken;
//
//import com.hubert.core.security.dto.User;
//
///**
// * openId 授权校验    开放资源  Oauth整合
// * @author Hubrt
// *
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class OpenIDLoginConfig_Backup1 extends WebSecurityConfigurerAdapter{
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//        .authorizeRequests()
//            .antMatchers("/resources/**").permitAll()
//            .anyRequest().authenticated()
//            .and()
//        .openidLogin()
//            .loginPage("/login")
//            .permitAll()
//            .authenticationUserDetailsService(new CustomUserDetailsService())
//            .attributeExchange("https://www.google.com/.*");
//		
//		//关闭csrf 防止循环定向
//	     http.csrf().disable();
//	}
//	
//	
//	@Bean(name = "myAuthenticationManager")
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    class CustomUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
//
//        @Override
//        public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
//            //用google / yahoo / tencentQQ 等授权登录后获取
//        	return new User(token.getName(), "", AuthorityUtils.createAuthorityList("ROLE_USER"));
//        }
//    }
//	
//	
//}
