package com.zm.v13searchserver;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.zm.v13.mapper")
public class V13SearchServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(V13SearchServerApplication.class, args);
    }

}
