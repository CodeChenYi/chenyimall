package com.chenyi.mall.search.com.chenyi.guilmall.service;

import com.chenyi.mall.search.com.chenyi.guilmall.dto.SearchParamsDTO;
import com.chenyi.mall.search.com.chenyi.guilmall.vo.SearchResultVO;

public interface MallSearchService  {
    /**
     * 商城检索
     * @param searchParamsDto
     * @return
     */
    SearchResultVO getSearchResult(SearchParamsDTO searchParamsDto);
}
