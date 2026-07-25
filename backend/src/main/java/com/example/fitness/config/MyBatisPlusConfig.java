package com.example.fitness.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.fitness.mapper")
public class MyBatisPlusConfig {
}
