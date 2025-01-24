package com.example.application.configuration;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    private static final String TITLE = "Сервис по подбору фильмов";
    private static final String DESCRIPTION = "Сервис работает с фильмами и оценками пользователей, а также возвращает фильмы, рекомендованные к просмотру";

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> openApi.info(new Info().title(TITLE).description(DESCRIPTION));
    }
}
