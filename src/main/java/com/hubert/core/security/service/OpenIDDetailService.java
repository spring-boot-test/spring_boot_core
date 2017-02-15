//package com.hubert.core.security.service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import com.hubert.core.security.dto.OpenUser;
//
//@Component
//public class OpenIDDetailService implements UserDetailsService{
//	
//	@Autowired
//    private LoginService loginService;
//
//    @Autowired
//    private RoleService roleService;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		 if (StringUtils.isBlank(username)) {
//			 throw new UsernameNotFoundException("用户名为空");
//	     }
//		 //用户是否在数据库中
//		 OpenUser login = loginService.findByUsername(username);
//		 if(null==login){
//			 throw new UsernameNotFoundException("用户不存在");
//		 }
//		 //用户权限
//		 Set<GrantedAuthority> authorities = new HashSet<>();
//		 roleService.getRoles(login.getId()).forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));
//
//		 
//		 return new User(username, login.getPassword(),
//	                true,//是否可用
//	                true,//是否过期
//	                true,//证书不过期为true
//	                true,//账户未锁定为true
//	                authorities);
//	}
//
//}
