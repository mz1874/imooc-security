package com.candy.mz.config;

import com.candy.mz.authentication.ImoocAuthenticationErrorHandler;
import com.candy.mz.authentication.ImoocAuthenticationSuccessHandler;
import com.candy.mz.properties.SecurityProperties;
import com.candy.mz.properties.filter.VerificationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author: candy
 * @date: 2020/7/18
 * @description :
 **/
@Configuration
public class BrowserSecurityConfigs extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImoocAuthenticationSuccessHandler imoocAuthenticationSuccessHandler;


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ImoocAuthenticationErrorHandler imoocAuthenticationErrorHandler;

    public void info() {
        System.out.printf(securityProperties.toString());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        VerificationFilter verificationFilter = new VerificationFilter();
        verificationFilter.setAuthenticationFailureHandler(imoocAuthenticationErrorHandler);
        /*表单进行认证*/
        http.addFilterBefore(verificationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/user/login")
                /*登录成功后的控制器*/
                .successHandler(imoocAuthenticationSuccessHandler)
                .failureHandler(imoocAuthenticationErrorHandler)
        /*HttpBasic方式认证*/
//        http.httpBasic()
                .and()
                /*对请求进行授权*/
                .authorizeRequests()
                /*对此不需要进行身份认证*/
                .antMatchers(
                        "/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/verification/code/image"
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
