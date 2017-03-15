package com.hubert.core.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hubert.core.security.dto.User;

@Service
public class UserService implements UserDetailsService{

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//通过数据库查询用户信息权限  --or-- http请求去用户权限系统获取用户信息和权限
		User user = null; 
		//user = userMapper.selectByUsername(username);

        if( user == null ){
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        }

        return user;
	}

}
