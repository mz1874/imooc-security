package com.candy.mz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: candy
 * @date: 2020/7/18
 * @description :
 **/
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*表单进行认证*/
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/user/login")
        /*HttpBasic方式认证*/
//        http.httpBasic()
                .and()
                /*对请求进行授权*/
                .authorizeRequests()
                /*对此不需要进行身份认证*/
                .antMatchers(
                    "/authentication/require",
                        "/login.html"
                ).permitAll()
                /*任何的请求*/
                .anyRequest()
                /*都需要认证*/
                .authenticated()
                /*关闭跨站伪造请求*/
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
