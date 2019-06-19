package com.zm.v13.prodectservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.zm.v13.api.IProductTypeService;
import com.zm.v13.common.base.BaseServiceImpl;
import com.zm.v13.common.base.IBaseDao;
import com.zm.v13.common.base.IBaseService;
import com.zm.v13.entity.TProductType;
import com.zm.v13.mapper.TProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<TProductType> implements IProductTypeService {
    @Autowired
    private TProductTypeMapper typeMapper;

    @Override
    public IBaseDao<TProductType> getBaseDao() {
        return typeMapper;
    }
}
