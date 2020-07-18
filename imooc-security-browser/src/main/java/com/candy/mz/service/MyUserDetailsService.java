package com.candy.mz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author: candy
 * @date: 2020/7/18
 * @description :
 **/

@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录用户名 -- >  {}", username);
        /**
         * 三个参数构造器
         * @parmas username
         *
         * @params password
         *
         * @Params authorities 权限
         *
         * 剩余参数请参考 UserDetailsService
         *
         * 第一次密码 $2a$10$o1qyeoVIak70M0E3ER/rfe.iTYMiXlyEqI.w9.iGCWiMr7eMAm7n6
         *
         * 第二次密码 $2a$10$/AiyfxGhKcUDBejoALZJnupxITGJQGMzjf7hS8zRPPDtQxBn4hlXu
         */
        String password = passwordEncoder.encode("1234");
        logger.info("登录密码 -- >  {}", password);
        User user = new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
