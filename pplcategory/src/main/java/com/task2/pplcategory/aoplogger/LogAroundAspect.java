package com.task2.pplcategory.aoplogger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAroundAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LogAroundAspect.class);

    @Around("@annotation(LogMethodParam)")
    public Object logMethodParam(ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info(String.format("Parameter passed to method %s is: %s ", joinPoint.getSignature().getName(),Arrays.toString(joinPoint.getArgs())));
        return joinPoint.proceed();
    }

}
