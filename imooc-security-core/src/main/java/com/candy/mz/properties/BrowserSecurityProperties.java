package com.candy.mz.properties;

import com.candy.mz.properties.enums.LoginType;

/**
 * @author: candy
 * @date: 2020/7/19
 * @description :
 **/

public class BrowserSecurityProperties {

    private String loginPage = "/login.html";

    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
