package com.candy.mz.properties.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @author by wangchong
 * @Description
 * @Date 2020/7/21 11:42
 */
public class VerificationCodeException extends AuthenticationException {
    public VerificationCodeException(String explanation) {
        super(explanation);
    }


}
