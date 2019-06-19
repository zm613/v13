package com.zm.v13centerweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.zm.v13.api.IProductService;
import com.zm.v13.api.ISearchService;
import com.zm.v13.common.pojo.ResultBean;
import com.zm.v13.entity.TProduct;
import com.zm.v13.pojo.TProductVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品的控制器层，分布式的消费层
 *
 * @author zm
 */
@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    private IProductService iProductService;

    @Reference
    private ISearchService searchService;

    /**
     * 根据ID查询商品
     *
     * @param id
     * @return
     */
    @RequestMapping("get/{id}")
    @ResponseBody
    public TProduct getById(@PathVariable Long id) {
        return iProductService.selectByPrimaryKey(id);
    }

    /**
     * 查询全部商品
     *
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list(Model model) {
        List<TProduct> list = iProductService.list();
        model.addAttribute("list", list);
        return "product/list";
    }

    /**
     * 商品分页
     *
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("page/{pageIndex}/{pageSize}")
    public String page(@PathVariable Integer pageIndex, @PathVariable Integer pageSize, Model model) {
        PageInfo<TProduct> page = iProductService.page(pageIndex, pageSize);
        model.addAttribute("page", page);
        return "product/list";
    }

    /**
     * 添加商品
     *
     * @param productVO
     * @return
     */
    @PostMapping("add")
    public String add(TProductVO productVO) {
        Long id = iProductService.save(productVO);
        searchService.synDataById(id);
        return "redirect:/product/page/1/1";
    }

    /**
     * 根据ID删除商品，实际是修改商品字段为false
     *
     * @param id
     * @return
     */
    @PostMapping("delById/{id}")
    @ResponseBody
    public ResultBean deleteByPrimaryKey(@PathVariable Long id) {
        int column = iProductService.deleteByPrimaryKey(id);
        if (column > 0) {
            return new ResultBean("200", "删除成功");
        }
        return new ResultBean("404", "删除失败，你懂的");
    }

    /**
     * 根据全选来批量删除商品
     *
     * @param ids
     * @return
     */
    @PostMapping("batchDel")
    @ResponseBody
    public ResultBean batchDel(@RequestParam List<Long> ids) {
        Long column = iProductService.batchDel(ids);
        if (column > 0) {
            return new ResultBean("200", "删除成功");
        }
        return new ResultBean("404", "删除失败，你懂的");
    }
}
