package com.chenyi.gulimall.product.web;

import com.chenyi.gulimall.product.entity.CategoryEntity;
import com.chenyi.gulimall.product.service.CategoryService;
import com.chenyi.gulimall.product.vo.CategoryEntityTwoVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Resource
    private CategoryService categoryService;


    @GetMapping(value = {"/"})
    public String getIndex(Model model) {
        // 查询一级分类
        List<CategoryEntity> categoryEntityVOList = categoryService.getCategoryLevelOne();

        model.addAttribute("categoryList", categoryEntityVOList);

        return "gulimallIndex";
    }

    @ResponseBody
    @GetMapping("/index/catalog.json")
    public Map<String, List<CategoryEntityTwoVO>> categoryLevelJson() {
        return categoryService.categoryLevelJson();
    }

    @ResponseBody
    @GetMapping("/list.html")
    public String getList() {
        return "";
    }
}
