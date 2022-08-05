package com.chenyi.mall.common.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取Servlet对象
 * @author chenyi
 * @className ServletUtils
 * @date 2022/5/8 0:40
 */
public class ServletUtils {

    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    public static ServletRequestAttributes getServletRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) requestAttributes;
    }

    public static String getCookies()  {
        HttpServletRequest request = getRequest();
        String token = request.getHeader("token");
        Cookie[] cookies = request.getCookies();
        if (StringUtils.isEmpty(token) && cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("chenyi_mall".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }
        return token;
    }

    public static String getHeader(String headerName) {
        HttpServletRequest request = getRequest();
        return request.getHeader(headerName);
    }
}
