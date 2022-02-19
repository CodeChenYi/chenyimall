package com.chenyi.gulimall.search.com.chenyi.guilmall.web;

import com.chenyi.gulimall.search.com.chenyi.guilmall.service.MallSearchService;
import com.chenyi.gulimall.search.com.chenyi.guilmall.dto.SearchParamsDTO;
import com.chenyi.gulimall.search.com.chenyi.guilmall.vo.SearchResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class SearchController {

    @Resource
    private MallSearchService mallSearchService;

    @GetMapping(value = {"/","/search.html"})
    public String search() {
        return "search";
    }


    @GetMapping("/list.html")
    public ModelAndView getList(SearchParamsDTO searchParamsDto) {

        SearchResultVO searchResult = mallSearchService.getSearchResult(searchParamsDto);

        ModelAndView search = new ModelAndView("search");
        search.addObject("searchResult", searchResult);

        return search;
    }

}
