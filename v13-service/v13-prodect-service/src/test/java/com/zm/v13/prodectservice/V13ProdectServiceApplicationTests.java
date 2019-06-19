package com.zm.v13.prodectservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zm.v13.api.IProductService;
import com.zm.v13.api.IProductTypeService;
import com.zm.v13.entity.TProduct;
import com.zm.v13.entity.TProductType;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V13ProdectServiceApplicationTests {
    @Autowired
    private IProductService iProductService;

    @Autowired
    private IProductTypeService typeService;
    @Test
    public void contextLoads() {
        List<TProductType> list = typeService.list();
        System.out.println(list.size());
    }

    @Test
    public void productTest() {
        List<TProduct> list = iProductService.list();
        System.out.println(list.size());
    }



}
