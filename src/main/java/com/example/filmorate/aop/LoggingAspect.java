package com.example.filmorate.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("topicName", message);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("----------------------------");
                System.out.println("Сообщение отправелно в Kafka");
                System.out.println(result.getRecordMetadata());
                System.out.println("----------------------------");
            } else {
                System.out.println("Произошла ошибка");
            }
        });
    }

    @Before("@within(org.springframework.web.bind.annotation.RestController)")
    public void before(JoinPoint joinPoint) {
        String signature = joinPoint.getSignature().toShortString();
        String arguments = Arrays.stream(joinPoint.getArgs()).map(Object::toString).collect(Collectors.joining(", "));
        String message = "Вызов метода: " + signature.replace("..", arguments);

//        log.info(message);
        sendMessage(message);
    }
}
