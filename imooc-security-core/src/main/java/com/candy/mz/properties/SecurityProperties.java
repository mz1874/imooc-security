package com.candy.mz.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: candy
 * @date: 2020/7/19
 * @description :
 **/

@ConfigurationProperties(value = "imooc.security")
public class SecurityProperties {

    private BrowserSecurityProperties browser = new BrowserSecurityProperties();

    public BrowserSecurityProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserSecurityProperties browser) {
        this.browser = browser;
    }
}
