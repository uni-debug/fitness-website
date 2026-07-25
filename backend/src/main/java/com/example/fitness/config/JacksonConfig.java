package com.example.fitness.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    public JacksonConfig(Jackson2ObjectMapperBuilder builder) {
        // 配置Jackson不转义Unicode字符
        builder.featuresToDisable(
            com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII
        );
    }
}
