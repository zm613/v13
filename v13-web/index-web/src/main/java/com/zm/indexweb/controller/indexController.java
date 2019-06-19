package com.zm.indexweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zm.v13.api.IProductTypeService;
import com.zm.v13.entity.TProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("index")
public class indexController {
    @Reference
    private IProductTypeService typeService;

    @RequestMapping("home")
    public String showHome(Model model) {
        List<TProductType> typeList = typeService.list();
        model.addAttribute("typeList",typeList);
        return "home";
    }
}
