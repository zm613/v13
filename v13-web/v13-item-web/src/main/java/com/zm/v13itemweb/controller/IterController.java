package com.zm.v13itemweb.controller;

import com.zm.v13.api.IProductService;
import com.zm.v13.common.pojo.ResultBean;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("item")
public class IterController {
    @Reference
    private IProductService productService;
    @Autowired
    private Configuration configuration;

    @RequestMapping("creatHTMLById/{id}")
    public ResultBean creatHTMLById(@PathVariable Long id) {
        try {
            //获取模板
            Template template = configuration.getTemplate("item.ftl");
            //获取数据
            Map<String, Object> map = new HashMap<>();
            map.put("product", productService.selectByPrimaryKey(id));
            //输出
            //以classpath的方式来获取保存路径
            String path = ResourceUtils.getURL("classpath:static").getPath();
            String append = new StringBuilder(path).append(File.separator).append(".html").toString();
            FileWriter file = new FileWriter(append);
            //集结
            template.process(map, file);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultBean("200", "获取模板失败！");
        } catch (TemplateException e) {
            e.printStackTrace();
            return new ResultBean("200", "生成静态页面失败！");
        }
        return new ResultBean("200", "生成静态页面成功！");
    }
}
