package com.candy.mz.properties.filter;

import com.candy.mz.properties.exception.VerificationCodeException;
import com.candy.mz.properties.verification.ImageCode;
import com.candy.mz.properties.verification.VerificationController;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author by wangchong
 * @Description
 * @Date 2020/7/21 11:38
 */
public class VerificationFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals("/user/login", httpServletRequest.getRequestURI()) && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(),"post")) {
            try {
                VerificationCode(new ServletWebRequest(httpServletRequest));
            } catch (VerificationCodeException ex) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,ex);
                return;
            }
        }
        super.doFilter(httpServletRequest, httpServletResponse,filterChain);
    }

    private void VerificationCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException, VerificationCodeException {
        ImageCode imageCode = (ImageCode)sessionStrategy.getAttribute(servletWebRequest, VerificationController.SESSION_KEY);
        String code = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"imageCode");
        if (StringUtils.isBlank(code)) {
            throw new VerificationCodeException("验证码不能为空");
        }

        if (imageCode == null) {
            throw new VerificationCodeException("验证码不存在");
        }

        if (imageCode.isExpried()) {
            throw new VerificationCodeException("验证码已经过期");
        }

        if (!StringUtils.equals(imageCode.getCode(),code)) {
            throw new VerificationCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(servletWebRequest,VerificationController.SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }
}
