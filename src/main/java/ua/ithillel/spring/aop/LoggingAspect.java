package ua.ithillel.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
    @Pointcut("execution(* ua.ithillel.repo..*.*(..))")
    private void repositoryMethods() {

    }

    @Around("repositoryMethods()")
    public Object aroundRepositoryMethod(ProceedingJoinPoint pjp) throws Throwable{
        log.info("Before executing {} with params {}",
                pjp.getSignature(), Arrays.toString(pjp.getArgs()));

        Object returnValue = pjp.proceed();

        log.info("After executing {} returned value: {}", pjp.getSignature(), returnValue);
        return returnValue;
    }

    @AfterThrowing(pointcut = "repositoryMethods()", throwing = "e")
    public void afterThrowingFromRepositoryMethod(Exception e){
        log.error("Exception thrown: {}", e.getMessage());
    }
}
