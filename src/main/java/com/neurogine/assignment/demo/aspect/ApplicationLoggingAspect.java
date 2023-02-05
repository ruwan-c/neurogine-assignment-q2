package com.neurogine.assignment.demo.aspect;

import com.neurogine.assignment.demo.entity.ApplicationLog;
import com.neurogine.assignment.demo.service.ApplicationLogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Configuration
@Slf4j
/**
 * This Aspect will save audit entry automatically in the database table - APPLICATION_LOG for each endpoint call
 */
public class ApplicationLoggingAspect {
    @Autowired
    private ApplicationLogService applicationLogService;

    @Pointcut("@annotation(com.neurogine.assignment.demo.annotation.Log)")
    public void pointcut() {
    }

    //    @Around("pointcut()")
    @Around("execution(* com.neurogine.assignment.demo.controller.*.*(..))")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            //Execution method
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        //Save log
        saveLog(point, beginTime);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ApplicationLog applicationLog = new ApplicationLog();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        applicationLog.setMethod(className + "." + methodName + "()");
        Object[] args = joinPoint.getArgs();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            applicationLog.setParams(params);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        applicationLog.setEndPoint(request.getServletPath());
        applicationLog.setUsername("Test user");
        applicationLog.setRequestTime(LocalDateTime.now().toString());
        applicationLog.setOperation(request.getMethod());
        applicationLogService.saveApplicationLog(applicationLog);
    }

    @Before("execution(* com.neurogine.assignment.demo.repository.*.*(..))")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        log.info("Before Repository Method: " + methodName);
    }
}