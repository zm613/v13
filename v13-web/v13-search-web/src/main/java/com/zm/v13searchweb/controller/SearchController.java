package com.zm.v13searchweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zm.v13.api.ISearchService;
import com.zm.v13.common.pojo.PageResultBean;
import com.zm.v13.common.pojo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {
    @Reference
    private ISearchService searchService;

    @RequestMapping("queryByKeywords/{keyword}/{currentPage}")
    public String queryByKeywords(@PathVariable String keyword, @PathVariable Integer currentPage, Model model) {
        ResultBean resultBean = searchService.queryByKeywords(keyword, currentPage, 2);

        PageResultBean pageResultBean = new PageResultBean();
        pageResultBean.setList((List) resultBean.getData());
        pageResultBean.setPageNum(currentPage);

        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("result", resultBean);
        return "list";
    }
}
