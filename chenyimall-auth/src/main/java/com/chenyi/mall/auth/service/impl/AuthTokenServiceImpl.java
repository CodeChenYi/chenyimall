package com.chenyi.mall.auth.service.impl;

import com.chenyi.mall.auth.entity.LoginUser;
import com.chenyi.mall.auth.service.AuthTokenService;
import com.chenyi.mall.common.constant.ChenYiMallConstant;
import com.chenyi.mall.common.enums.ResultEnum;
import com.chenyi.mall.common.exception.ChenYiMallException;
import com.chenyi.mall.common.utils.JWTUtils;
import com.chenyi.mall.api.member.to.MemberInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.concurrent.TimeUnit;

/**
 * @author chenyi
 * @className AuthTokenServiceImpl
 * @date 2022/5/16 20:32
 */
@Slf4j
@Service
public class AuthTokenServiceImpl implements AuthTokenService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String infoByUserName(String userName, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (authenticate == null) {
            throw new ChenYiMallException(ResultEnum.MEMBER_LOGIN_FAIL.getCode(),
                    ResultEnum.MEMBER_LOGIN_FAIL.getMsg());
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long id = loginUser.getMemberInfo().getMember().getId();
        LoginUser user = (LoginUser) redisTemplate.opsForValue().get(ChenYiMallConstant.LOGIN_USER + id);
        if (user != null) {
            redisTemplate.delete(ChenYiMallConstant.LOGIN_USER + id);
        }
        // 生成jwt
        String jwt = JWTUtils.createToken(id.toString());
        // 将用户信息存储到redis中 存储7天
        redisTemplate.opsForValue().set(
                ChenYiMallConstant.LOGIN_USER + id,
                loginUser,
                ChenYiMallConstant.SEVEN_DAY_MILLIS_VALUE,
                TimeUnit.MILLISECONDS);
        // 设置cookie
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Cookie cookie = new Cookie("chenyi_mall", jwt);
        cookie.setDomain("cym.com");
        cookie.setPath("/");
        cookie.setMaxAge((int) (ChenYiMallConstant.SEVEN_DAY_MILLIS_VALUE / 1000L));
        requestAttributes.getResponse().addCookie(cookie);
        return jwt;
    }

    @Override
    public MemberInfo verifyToken(String token) {
        if (!StringUtils.isEmpty(token)) {
            try {
                String userId = (String) JWTUtils.getPayload(token);
                log.debug(userId);
                LoginUser loginUser = (LoginUser) redisTemplate.opsForValue()
                        .get(ChenYiMallConstant.LOGIN_USER + userId);
                log.debug("loginUser {}", loginUser);
                if (loginUser != null) {
                    return loginUser.getMemberInfo();
                }
            } catch (Exception e) {
                log.error("jwt验证错误：{}", e.getMessage());
                e.getStackTrace();
            }
        }
        return null;
    }
}
