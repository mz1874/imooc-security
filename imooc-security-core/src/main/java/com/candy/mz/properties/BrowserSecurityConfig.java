package com.candy.mz.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: candy
 * @date: 2020/7/19
 * @description : 检查好包名 若 当前的package 不在扫描路径上时则无法注入
 **/
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class BrowserSecurityConfig {
}
