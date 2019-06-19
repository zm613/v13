package com.zm.v13searchserver;

import com.zm.v13.api.ISearchService;
import com.zm.v13.common.pojo.ResultBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V13SearchServerApplicationTests {

    @Autowired
    private ISearchService searchService;
    @Test
    public void contextLoads() {
        ResultBean resultBean = searchService.queryByKeywords("程序员",0,2);
        System.out.println(resultBean.getData());
    }

}
