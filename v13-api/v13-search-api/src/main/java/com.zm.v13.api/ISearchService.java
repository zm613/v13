package com.zm.v13.api;

import com.zm.v13.common.pojo.ResultBean;

public interface ISearchService {
    /**
     * 如果没有关键词索引，就全部搜索
     * 全量同步
     *
     * @return
     */
    public ResultBean synData();

    /**
     * 增量同步
     *
     * @param id
     * @return
     */
    public ResultBean synDataById(Long id);

    /**
     * 根据关键词索引
     *
     * @param keywords
     * @return
     */
    public ResultBean queryByKeywords(String keywords,Integer currentPage,Integer pageSize);
}
