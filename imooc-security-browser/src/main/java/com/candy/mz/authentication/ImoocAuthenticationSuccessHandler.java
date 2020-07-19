package com.candy.mz.authentication;

import com.candy.mz.properties.BrowserSecurityProperties;
import com.candy.mz.properties.SecurityProperties;
import com.candy.mz.properties.enums.LoginType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: candy
 * @date: 2020/7/19
 * @description : AuthenticationSuccessHandler 登录成功处理  具有多个子类
 * SavedRequestAwareAuthenticationSuccessHandler
 **/

@Component
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    private Logger logger = LoggerFactory.getLogger(ImoocAuthenticationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("login success");
        /*处理json*/
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            logger.info("result json ");
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            /*跳转页面*/
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
