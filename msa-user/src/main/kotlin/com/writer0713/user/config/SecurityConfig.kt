package com.writer0713.user.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher

@Configuration
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        // h2-console 사용을 위한 csrf disable
        http.csrf { csrf -> csrf.disable() }

        // request 기반 authorization
        http.authorizeHttpRequests { auth ->
            auth
                .requestMatchers(
                    antMatcher("/users/**"),
                    antMatcher(("/h2-console/**")),
                ).permitAll()
        }

        // h2-console iframe 이 잘 나오도록 하기 위함
        http.headers { headerConfig -> headerConfig.frameOptions { it.sameOrigin() } }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()
}
