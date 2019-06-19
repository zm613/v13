package com.zm.v13.api;

import com.github.pagehelper.PageInfo;
import com.zm.v13.common.base.IBaseService;
import com.zm.v13.entity.TProduct;
import com.zm.v13.pojo.TProductVO;

import java.util.List;

public interface IProductService extends IBaseService<TProduct> {
    //以下写特殊的方法
    public PageInfo<TProduct> page(Integer pageIndex, Integer pageSize);

    //添加
    public Long save(TProductVO tProductVO);

    //批量删除
    public Long batchDel(List<Long> ids);
}
