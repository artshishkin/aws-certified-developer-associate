package com.artarkatesoft.coronavirustracker.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Aspect
@Configuration
@Slf4j
public class MethodExecutionTimeAspect {
//    private Logger log = LoggerFactory.getLogger(this.getClass());

//    @Around("@annotation(LogExecutionTime)")
//    public Object calculateExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        Object[] args = joinPoint.getArgs();
//        logger.info("Entry in method {}",joinPoint);
//        Object result = joinPoint.proceed();
//        long timeTaken = System.currentTimeMillis() - startTime;
//        if (args.length > 5 || (args.length >= 1 && args[0] instanceof List && ((List) (args[0])).size() > 5))
//            logger.info("Time Taken by {} is {} ms. Arguments count:{}.", joinPoint, timeTaken, args.length);
//        else if (args.length == 0)
//            logger.info("Time Taken by {} is {} ms.", joinPoint, timeTaken);
//        else
//            logger.info("Time Taken by {} is {} ms. Arguments count:{}. Arguments are {}", joinPoint, timeTaken, args.length, args);
//        return result;
//    }

    @Around("execution(* com.artarkatesoft.coronavirustracker.services.*.*(..)) || @annotation(LogExecutionTime)")
    public Object calculateExecutionTime2(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.trace("Entry in method {}", joinPoint);
        Object[] args = joinPoint.getArgs();
        Object result = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        if (args.length > 5 || (args.length >= 1 && args[0] instanceof List && ((List) (args[0])).size() > 5))
            log.trace("Time Taken by {} is {} ms. Arguments count:{}.", joinPoint, timeTaken, args.length);
        else if (args.length == 0)
            log.trace("Time Taken by {} is {} ms.", joinPoint, timeTaken);
        else
            log.trace("Time Taken by {} is {} ms. Arguments count:{}. Arguments are {}", joinPoint, timeTaken, args.length, args);
        return result;
    }


}

