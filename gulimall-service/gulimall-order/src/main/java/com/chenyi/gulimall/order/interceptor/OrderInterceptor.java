package com.chenyi.gulimall.order.interceptor;

import com.chenyi.gulimall.auth.feign.AuthFeignService;
import com.chenyi.gulimall.common.enums.ResultEnum;
import com.chenyi.gulimall.common.utils.R;
import com.chenyi.gulimall.member.to.MemberInfo;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenyi
 * @className CartInterceptor
 * @date 2022/6/29 21:30
 */
public class OrderInterceptor implements HandlerInterceptor {

    @Resource
    private AuthFeignService authFeignService;

    public static ThreadLocal<MemberInfo> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        String token = request.getHeader("token");
        Cookie[] cookies = request.getCookies();
        if (StringUtils.isEmpty(token) && cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("chenyi_mall".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }
        if (!StringUtils.isEmpty(token)) {
            R r = authFeignService.verifyToken(token);
            if (ResultEnum.SUCCESS.getCode().equals(r.getCode())) {
                MemberInfo memberInfo = r.get("memberInfo", MemberInfo.class);
                threadLocal.set(memberInfo);
                return true;
            }
        }
        // 如果没有带cookie那么就重定向到登录页面
        response.sendRedirect("http://auth.gulimall.com");
        return false;
    }
}
