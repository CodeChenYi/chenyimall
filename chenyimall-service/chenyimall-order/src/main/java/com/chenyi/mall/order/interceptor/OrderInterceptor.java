package com.chenyi.mall.order.interceptor;

import com.chenyi.mall.api.auth.feign.AuthFeignService;
import com.chenyi.mall.api.member.to.MemberInfo;
import com.chenyi.mall.common.enums.ResultEnum;
import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于拦截请求，在请求前做一些前置造作
 * @author chenyi
 * @className CartInterceptor
 * @date 2022/6/29 21:30
 */
@Slf4j
public class OrderInterceptor implements HandlerInterceptor {

    @Resource
    private AuthFeignService authFeignService;

    public static final ThreadLocal<MemberInfo> MEMBER_THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        String requestToken = request.getHeader("RequestToken");
        if (requestToken != null) {
            return true;
        }
        // 获取cookie信息
        String token = ServletUtils.getCookies();
        log.info(token);
        // 保存token
        if (!StringUtils.isEmpty(token)) {
            R r = authFeignService.verifyToken(token);
            if (ResultEnum.SUCCESS.getCode().equals(r.getCode())) {
                MemberInfo memberInfo = r.get("memberInfo", MemberInfo.class);
                MEMBER_THREAD_LOCAL.set(memberInfo);
                return true;
            }
        }
        // 如果没有带cookie那么就重定向到登录页面
        response.sendRedirect("http://auth.cym.com");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 清理ThreadLocal
        MEMBER_THREAD_LOCAL.remove();
    }
}
