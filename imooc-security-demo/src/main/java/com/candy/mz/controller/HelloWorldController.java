package com.candy.mz.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: candy
 * @date: 2020/7/18
 * @description :
 **/

@RequestMapping("user")
@RestController
public class HelloWorldController {

    @GetMapping(value = "helloWorld")
    public String helloWorld() {
        return "success";
    }

    @GetMapping(value = "getMe")
    public Object getMe() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取 authentication
     * @param authentication require false
     * @return
     */
    @GetMapping(value = "getMe2")
    public Object getMe2(Authentication authentication) {
        return authentication;
    }

    /**
     * 获取登录对象
     * @param userDetails require false
     * @return
     */
    @GetMapping(value = "getMe3")
    public Object getMe3(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }
}
