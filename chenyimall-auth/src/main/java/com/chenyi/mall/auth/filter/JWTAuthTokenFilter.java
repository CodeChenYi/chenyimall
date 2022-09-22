package com.chenyi.mall.auth.filter;

import cn.hutool.http.ContentType;
import com.chenyi.mall.auth.entity.LoginUser;
import com.chenyi.mall.common.constant.ChenYiMallConstant;
import com.chenyi.mall.common.enums.ResultEnum;
import com.chenyi.mall.common.exception.ChenYiMallException;
import com.chenyi.mall.common.utils.JSONUtils;
import com.chenyi.mall.common.utils.JWTUtils;
import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenyi
 * @className JWTAuthTokenFilter
 * @date 2022/5/17 0:46
 */
@Slf4j
@Component
public class JWTAuthTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filter)
            throws ServletException, IOException {

        String token = ServletUtils.getCookies();
        if (!StringUtils.isEmpty(token)) {
            // 解析token
            try {
                String jwtToken = (String) JWTUtils.getPayload(token);
                log.debug("userId: {}", jwtToken);
                LoginUser loginUser = (LoginUser) redisTemplate.opsForValue()
                        .get(ChenYiMallConstant.LOGIN_USER + jwtToken);
                log.debug("loginUser == null {}", loginUser == null);
                if (loginUser != null) {
                    // 设置到SecurityContextHolder中
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(loginUser, null, null);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    throw new ChenYiMallException(10005, "登录信息已过期！");
                }
            } catch (Exception e) {
                log.error("jwt验证错误：{}", e.getMessage());
                response.setStatus(403);
                response.setContentType(ContentType.JSON.getValue());
                response.getWriter().write(JSONUtils.toJSONString(R.error(ResultEnum.USER_AUTOGRAPH_VERIFY_FAIL.getCode(),
                        ResultEnum.USER_AUTOGRAPH_VERIFY_FAIL.getMsg())));
            }
        }

        filter.doFilter(request, response);
    }
}
