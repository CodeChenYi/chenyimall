package com.chenyi.gulimall.search.com.chenyi.guilmall.service.impl;

import com.chenyi.gulimall.search.com.chenyi.guilmall.service.MallSearchService;
import com.chenyi.gulimall.search.com.chenyi.guilmall.dto.SearchParamsDTO;
import com.chenyi.gulimall.search.com.chenyi.guilmall.vo.SearchResultVO;
import org.springframework.stereotype.Service;

@Service("MallSearchServiceImpl")
public class MallSearchServiceImpl implements MallSearchService {

    @Override
    public SearchResultVO getSearchResult(SearchParamsDTO searchParamsDto) {

        if (searchParamsDto != null) {

        }

        return null;
    }


}
