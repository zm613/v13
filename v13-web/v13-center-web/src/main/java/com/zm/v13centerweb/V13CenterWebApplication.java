package com.zm.v13centerweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDubbo
@Import(FdfsClientConfig.class)
public class V13CenterWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(V13CenterWebApplication.class, args);
    }

}
