package com.example.filmorate.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("@within(org.springframework.web.bind.annotation.RestController)")
    public void before(JoinPoint joinPoint) {
        String signature = joinPoint.getSignature().toShortString();
        String arguments = Arrays.stream(joinPoint.getArgs()).map(Object::toString).collect(Collectors.joining(", "));

        log.info("Вызов метода: {}", signature.replace("..", arguments));
    }
}
