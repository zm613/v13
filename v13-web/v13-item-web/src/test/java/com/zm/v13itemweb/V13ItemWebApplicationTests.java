package com.zm.v13itemweb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V13ItemWebApplicationTests {
    @Autowired
    private Configuration configuration;

    @Test
    public void contextLoads() {
        try {
            //获取模板对象
            Template template = configuration.getTemplate("hello.ftl");
            //设置模板数据
            Map<String, Object> map = new HashMap<>();
            map.put("username", "zm");
            Writer writer = new FileWriter("");
            template.process(map, writer);
            System.out.println("生成静态页面成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}
