package com.test.newsmanagement.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpServletRequest request;

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)" +
            "|| within(@org.springframework.stereotype.Repository *)" +
            "|| within(@org.springframework.stereotype.Service *)" +
            "|| within(@org.springframework.stereotype.Component *)")
    public void beanPointcut() {
    }

    @Pointcut("execution(* com.test.newsmanagement.controller.*.*(..))")
    public void controllerPointcut() {
    }

    @Pointcut("execution(* com.test.newsmanagement.service.*.*(..))")
    public void servicePointcut() {
    }

    @AfterThrowing(pointcut = "beanPointcut()", throwing = "throwable")
    public void loggingAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        String bean = joinPoint.getSignature().getDeclaringTypeName();
        String method = joinPoint.getSignature().getName();

        logger.error("Exception in {}.{}() with cause = {}", bean, method, throwable.toString());
    }

    @Around("controllerPointcut()")
    public ResponseEntity<?> controllerLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        String parameters = Arrays.toString(joinPoint.getArgs());

        if (logger.isDebugEnabled()) {
            logger.debug("REQUEST ---> Method: {}. Url: {}. Request parameter[s] = {}", method, url, parameters);
        }

        ResponseEntity<?> response = (ResponseEntity<?>) joinPoint.proceed();

        if (logger.isDebugEnabled()) {
            logger.debug("RESPONSE <--- Method: {}. Url: {}. Response status: {}, Response body: {}",
                    method, url, response.getStatusCode(), response.getBody());
        }

        return response;
    }

    @Around("servicePointcut()")
    public Object serviceLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        String service = joinPoint.getSignature().getDeclaringTypeName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        if (logger.isDebugEnabled()) {
            logger.debug("ENTRY ---> Service: {}. Method: {}() with argument[s] = {}", service, method, args);
        }

        try {
            Object result = joinPoint.proceed();

            if (logger.isDebugEnabled()) {
                logger.debug("EXIT <--- Service: {}. Method: {}() with result = {}", service, method, result);
            }

            return result;
        } catch (IllegalArgumentException exception) {
            logger.error("Illegal argument: {} in {}.{}()", args, service, method);

            throw exception;
        }
    }
}
