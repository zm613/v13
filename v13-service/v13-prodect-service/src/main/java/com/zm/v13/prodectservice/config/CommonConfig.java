package com.zm.v13.prodectservice.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class CommonConfig {
    @Bean
    public PageHelper getPageHelper() {
        PageHelper pageHelper = new PageHelper();
        //设置属性
        Properties properties = new Properties();
        //
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "true");
        //
        pageHelper.setProperties(properties);

        return pageHelper;
    }
}
