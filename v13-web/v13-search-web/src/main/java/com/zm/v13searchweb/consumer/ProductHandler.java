package com.zm.v13searchweb.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zm.v13.api.ISearchService;
import com.zm.v13.common.constant.RabbitMQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 针对平台系统商品业务发送的消息做处理的消费端
 */
@Component
@RabbitListener(queues = RabbitMQConstant.PRODUCT_SEARCH_QUEUE)
public class ProductHandler {
    @Reference
    private ISearchService searchService;

    @RabbitHandler
    public void proceAdd(Long id) {
        System.out.println(id);
        searchService.synDataById(id);
    }

}
