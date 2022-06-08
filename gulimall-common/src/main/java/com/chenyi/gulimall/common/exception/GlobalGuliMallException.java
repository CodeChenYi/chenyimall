package com.chenyi.gulimall.common.exception;

import com.chenyi.gulimall.common.enums.ResultEnum;
import com.chenyi.gulimall.common.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理类
 * @author chenyi
 * @className GuliMallException
 * @date 2022/5/7 20:16
 */
@Slf4j
@RestControllerAdvice
public class GlobalGuliMallException {

    @ExceptionHandler(value = GuliMallException.class)
    public R GuliMallException(GuliMallException e) {
        log.error("业务发生异常！异常信息：{}", e.getMsg());
        return R.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public R noHandlerFoundException(NullPointerException e) {
        log.error("路径不存在！请检查请求路径：{}", e.getMessage());
        return R.error(ResultEnum.PATH_NOT_EXIST.getCode(), ResultEnum.PATH_NOT_EXIST.getMsg());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R methodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 获取数据校验错误信息
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, String> map = new HashMap<>(fieldErrors.size());
        fieldErrors.forEach(fieldError ->
            map.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        log.error("数据校验异常：异常信息是{}", map);
        return R.error(ResultEnum.PARAMETER_VERIFICATION_ERROR.getCode(),
                ResultEnum.PARAMETER_VERIFICATION_ERROR.getMsg()).put("data", map);
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    public R duplicateKeyException(Exception e) {
        log.error("数据库中已存在该记录{}", e.getMessage());
        return R.error(ResultEnum.SYSTEM_ALREADY_EXIST_RECORD.getCode(),
                ResultEnum.SYSTEM_ALREADY_EXIST_RECORD.getMsg());
    }

    // TODO 权限异常处理
    public R noPermission(Exception e) {
        log.error("没有权限，请联系管理员授权！{}", e.getMessage());
        return R.error(ResultEnum.NO_PERMISSION.getCode(), ResultEnum.NO_PERMISSION.getMsg());
    }

//    @ExceptionHandler(value = AuthenticationException.class)
//    public R BadCredentialsException(BadCredentialsException e) {
//        log.error("认证验证异常！{}", e.getMessage());
//        return R.error(ResultEnum.MEMBER_LOGIN_FAIL.getCode(), ResultEnum.MEMBER_LOGIN_FAIL.getMsg());
//    }

    @ExceptionHandler(value = JsonProcessingException.class)
    public R jsonHandlerException(Exception e) {
        log.error("json处理异常！异常信息：{}", e.getMessage());
        return R.error(ResultEnum.JSON_HANDLER_ERROR.getCode(), ResultEnum.JSON_HANDLER_ERROR.getMsg());
    }

    @ExceptionHandler(value = Throwable.class)
    public R AllException(Throwable e) {
        log.error("服务器发生异常！异常信息：{}，异常类型:{}", e.getMessage(), e.getClass());
        return R.error(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), ResultEnum.INTERNAL_SERVER_ERROR.getMsg());
    }

}
