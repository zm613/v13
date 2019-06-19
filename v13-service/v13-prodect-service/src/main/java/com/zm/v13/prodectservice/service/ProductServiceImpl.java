package com.zm.v13.prodectservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zm.v13.api.IProductService;
import com.zm.v13.api.ISearchService;
import com.zm.v13.common.base.BaseServiceImpl;
import com.zm.v13.common.base.IBaseDao;
import com.zm.v13.entity.TProduct;
import com.zm.v13.entity.TProductDesc;
import com.zm.v13.mapper.TProductDescMapper;
import com.zm.v13.mapper.TProductMapper;
import com.zm.v13.pojo.TProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl<TProduct> implements IProductService {

    @Autowired
    private TProductMapper productMapper;


    @Autowired
    private TProductDescMapper productDescMapper;

    @Override
    public IBaseDao<TProduct> getBaseDao() {
        return productMapper;
    }

    //分页
    @Override
    public PageInfo<TProduct> page(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<TProduct> list = list();
        PageInfo<TProduct> tProductPageInfo = new PageInfo<TProduct>(list, 5);
        return tProductPageInfo;
    }

    @Override
    @Transactional
    public Long save(TProductVO tProductVO) {
        //保存商品的基本信息
        TProduct product = tProductVO.getProduct();
        product.setFlag(true);
        //主键回填,insert是受影响的行数
        int insert = productMapper.insert(product);
        //保存商品的描述信息
        String productDesc = tProductVO.getProductDesc();
        TProductDesc tProductDesc = new TProductDesc();
        tProductDesc.setProductDesc(productDesc);
        tProductDesc.setProductId(product.getId());
        productDescMapper.insert(tProductDesc);
        //返回商品新增的主键
        return product.getId();
    }

    //批量删除
    @Override
    public Long batchDel(List<Long> ids) {

        return productMapper.batchUpdate(ids);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        TProduct product = new TProduct();
        product.setId(id);
        product.setFlag(false);
        return productMapper.updateByPrimaryKeySelective(product);
    }
}
