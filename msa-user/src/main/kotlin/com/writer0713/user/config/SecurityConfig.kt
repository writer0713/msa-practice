package com.writer0713.user.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.writer0713.user.config.filter.CustomAuthenticationFilter
import com.writer0713.user.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity(debug = false)
@Configuration
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val userService: UserService,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        // h2-console 사용을 위한 csrf disable
        http.csrf { csrf -> csrf.disable() }

        // request 기반 authorization
        http.authorizeHttpRequests { auth ->
            auth
                .requestMatchers("/h2-console/**")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/users")
                .permitAll()
                .anyRequest()
                .authenticated()
        }

        http.addFilter(customAuthenticationFilter())

        // h2-console iframe 이 잘 나오도록 하기 위함
        http.headers { headerConfig -> headerConfig.frameOptions { it.sameOrigin() } }

        return http.build()
    }

    fun customAuthenticationFilter(): CustomAuthenticationFilter {
        val customAuthenticationFilter = CustomAuthenticationFilter(objectMapper, userService, authenticationManager())
        return customAuthenticationFilter
    }

    @Bean
    fun authenticationManager(): AuthenticationManager = ProviderManager(listOf(authenticationProvider()))

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(userService)
        provider.setPasswordEncoder(passwordEncoder())
        return provider
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()
}
