package com.writer0713.user.controller

import com.writer0713.user.dto.Greeting
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class UserController(
    private val env: Environment,
    private val greeting: Greeting,
) {
    companion object {
        private val log = KotlinLogging.logger { }
    }

    @GetMapping("/health-check")
    fun status(): String = "It's Working in User Service"

    @GetMapping("/welcome")
    fun welcome(): String {
        val messageFromEnv = env.getProperty("greeting.message") ?: "no greeting.message"
        log.info { ">>> messageFromEnv : $messageFromEnv" }

        return greeting.message
    }
}
