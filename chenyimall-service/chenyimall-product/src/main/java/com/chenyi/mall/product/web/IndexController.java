package com.chenyi.mall.product.web;

import com.chenyi.mall.product.service.CategoryService;
import com.chenyi.mall.product.vo.CategoryEntityTwoVO;
import com.chenyi.mall.product.entity.CategoryEntity;
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

        return "chenyimallIndex";
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
