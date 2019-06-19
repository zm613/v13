package com.zm.v13searchserver.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.zm.v13.api.ISearchService;
import com.zm.v13.common.pojo.ResultBean;
import com.zm.v13.entity.TProduct;
import com.zm.v13.mapper.TProductMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements ISearchService {
    @Autowired
    private SolrClient solrClient;
    @Autowired
    private TProductMapper productMapper;

    @Override
    public ResultBean synData() {
        List<TProduct> list = productMapper.list();
        for (TProduct tProduct : list) {
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id", tProduct.getId());
            document.setField("product_name", tProduct.getName());
            document.setField("product_price", tProduct.getPrice());
            document.setField("product_images", tProduct.getImages());
            document.setField("product_sale_point", tProduct.getSalePoint());
            try {
                solrClient.add(document);
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
                return new ResultBean("404", "数据添加到索引库失败");
            }
            try {
                solrClient.commit();
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
                return new ResultBean("404", "数据提交到索引库失败");
            }
        }
        return new ResultBean("200", list);
    }

    @Override
    public ResultBean synDataById(Long id) {
        TProduct tProduct = productMapper.selectByPrimaryKey(id);
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id", tProduct.getId());
        document.setField("product_name", tProduct.getName());
        document.setField("product_price", tProduct.getPrice());
        document.setField("product_images", tProduct.getImages());
        document.setField("product_sale_point", tProduct.getSalePoint());
        try {
            solrClient.add(document);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean("404", "数据添加到索引库失败");
        }
        try {
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean("404", "数据提交到索引库失败");
        }
        return new ResultBean("200", document);
    }

    @Override
    public ResultBean queryByKeywords(String keywords,Integer currentPage,Integer pageSize) {
        //1.组装查询条件
        SolrQuery solrQuery = new SolrQuery();
        //做判断
        if (StringUtils.isAnyEmpty(keywords)) {
            solrQuery.setQuery("*:*");
        } else {
            solrQuery.setQuery("product_keywords:" + keywords);
        }

        //设置分页
        solrQuery.setStart(0);
        solrQuery.setRows(2);

        //设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("product_name");
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");
        try {
            //2.执行查询 documentList
            QueryResponse query = solrClient.query(solrQuery);
            SolrDocumentList results = query.getResults();
            List<TProduct> list = new ArrayList<>(5);

            //获取高亮信息
            Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();


            for (SolrDocument result : results) {
                TProduct tProduct = new TProduct();
                tProduct.setId(Long.parseLong(result.getFieldValue("id").toString()));
                //tProduct.setName(result.getFieldValue("product_name").toString());
                tProduct.setImages(result.getFieldValue("product_images").toString());
                tProduct.setPrice(Long.parseLong(result.getFieldValue("product_price").toString()));
                tProduct.setSalePoint(result.getFieldValue("product_sale_point").toString());

                //单独为高亮做配置
                Map<String, List<String>> id = highlighting.get(result.getFieldValue("id"));
                //获取商品名称对应的高亮信息
                List<String> productNameHigh = id.get("product_name");
                if (productNameHigh == null || productNameHigh.isEmpty()) {
                    //没有高亮信息
                    tProduct.setName(result.getFieldValue("product_name").toString());
                } else {
                    //有高亮信息
                    tProduct.setName(productNameHigh.get(0));
                }
                list.add(tProduct);
            }
            return new ResultBean("200", list);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean("404", "执行查询失败");
        }
    }
}
