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
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


@Controller
@RequestMapping("item")
public class ItemController {
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

//    @RequestMapping("batchCreateHTML")
//    public ResultBean batchCreateHTML(@RequestParam List<Long> ids) {
//        //什么时候用多线程？
//        //没有先后生成的顺序要求
//        //JDK提供的三种线程池：
//        // 单个线程的线程池：顺序执行一些任务，顺序执行效果
//        //队列太长，导致内存出现OOM
//        //ExecutorService pool = Executors.newSingleThreadExecutor();
//
//        //队列太长，导致内存出现OOM
//        //ExecutorService pool = Executors.newFixedThreadPool(10);
//
//        //创建线程对象太多
//        //ExecutorService pool = Executors.newCachedThreadPool();
//
//        //自定义的方式来创建
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100,
//                10, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(100));
//
//
//    }
}
