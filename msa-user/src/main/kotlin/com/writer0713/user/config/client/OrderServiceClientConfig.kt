package com.writer0713.user.config.client

import feign.Logger.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrderServiceClientConfig {
    @Bean
    fun feignLoggerLevel(): Level = Level.FULL
}
