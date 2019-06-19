package com.zm.v13.mapper;


import com.zm.v13.common.base.IBaseDao;
import com.zm.v13.entity.TProduct;

import java.util.List;

public interface TProductMapper extends IBaseDao<TProduct> {

    Long batchUpdate(List<Long> ids);
}