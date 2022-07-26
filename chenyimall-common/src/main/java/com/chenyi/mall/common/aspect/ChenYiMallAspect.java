package com.chenyi.mall.common.aspect;


import com.chenyi.mall.common.annotation.LogAnnotation;
import com.chenyi.mall.common.enums.ResultEnum;
import com.chenyi.mall.common.exception.ChenYiMallException;
import com.chenyi.mall.common.utils.IPUtils;
import com.chenyi.mall.common.utils.JSONUtils;
import com.chenyi.mall.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 日志切面类
 * @author chenyi
 * @className mallAspect
 * @date 2022/5/7 23:24
 */
@Slf4j
@Component
@Aspect
public class ChenYiMallAspect {

    @Pointcut("@annotation(com.chenyi.mall.common.annotation.LogAnnotation)")
    public void logAnnotation() {
    }

    /**
     * 方法正常返回之后
     * @param joinPoint 封装了一些方法的参数信息
     * @param jsonResult 返回成功的json
     */
    @AfterReturning(value = "logAnnotation()", returning = "jsonResult")
    public void logAfter(JoinPoint joinPoint, Object jsonResult) {
        log.info("---------->:方法正常返回");
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     *
     * @param joinPoint 封装了一些方法的参数信息
     * @param e 发生的异常信息
     */
    @AfterThrowing(value = "logAnnotation()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
        log.info("---------->:方法异常返回");
        handleLog(joinPoint, e, null);
    }

    private void handleLog(JoinPoint joinPoint, Exception e, Object jsonResult) {
        // TODO 新建实体类，保存日志信息
        // 获取注解信息
        LogAnnotation annotation = getAnnotation(joinPoint);
        if (annotation == null) {
            return;
        }
        // 设置注解信息
        String title = annotation.title();
        String module = annotation.module();
        String operateType = annotation.operateType().getMsg();

        // 获取请求路径
        HttpServletRequest request = ServletUtils.getRequest();
        String requestURI = request.getRequestURI();
        // 获取ip地址
        String ipAddress = IPUtils.getIpAddress(request);
        // 获取请求类型
        String method = request.getMethod();
        // 将返回参数构造为String
        if (jsonResult != null) {
            String s = JSONUtils.toJSONString(jsonResult);
        }
        // 获取请求参数
        String requestParam = argsArrayToString(joinPoint.getArgs());

        if (e != null) {
            String message = e.getMessage();
            Integer code = ResultEnum.INTERNAL_SERVER_ERROR.getCode();
            // 判断异常类型进行返回
            if (e instanceof ChenYiMallException) {
                ChenYiMallException exception = (ChenYiMallException) e;
                code = exception.getCode();
            }
            if (e instanceof MethodArgumentNotValidException) {
                code = ResultEnum.PARAMETER_VERIFICATION_ERROR.getCode();
            }
            if (e instanceof DuplicateKeyException) {
                code = ResultEnum.SYSTEM_ALREADY_EXIST_RECORD.getCode();
            }
        }
        // 获取方法全类名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String classMethodName = className + "." + methodName + "()";
    }

    /**
     * 获取注解
     * @param joinPoint
     * @return
     */
    private LogAnnotation getAnnotation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(LogAnnotation.class);
        }
        return null;
    }

    /**
     * 参数拼装
     *
     * @param paramsArray
     * @return
     */
    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (o == null) {
                    continue;
                }
                // 判断当前对象是否要过滤
                if (!isFilterObject(o)) {
                    String jsonObj = JSONUtils.toJSONString(o);
                    params.append(jsonObj).append(" ");
                }
            }
        }
        return params.toString().trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    public boolean isFilterObject(final Object o) {
        return o instanceof MultipartFile || o instanceof HttpServletRequest
                || o instanceof HttpServletResponse;
    }
}
