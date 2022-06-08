package com.chenyi.gulimall.auth.filter;

import cn.hutool.http.ContentType;
import com.chenyi.gulimall.auth.entity.LoginUser;
import com.chenyi.gulimall.common.constant.GuliMallConstant;
import com.chenyi.gulimall.common.enums.ResultEnum;
import com.chenyi.gulimall.common.utils.JSONUtils;
import com.chenyi.gulimall.common.utils.JWTUtils;
import com.chenyi.gulimall.common.utils.R;
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

        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            // 解析token
            try {
                String jwtToken = (String) JWTUtils.getPayload(token);
                log.info(jwtToken);
                LoginUser loginUser = (LoginUser) redisTemplate.opsForValue()
                        .get(GuliMallConstant.LOGIN_USER + jwtToken);
                if (loginUser != null) {
                    // 设置到SecurityContextHolder中
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(loginUser, null, null);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
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
