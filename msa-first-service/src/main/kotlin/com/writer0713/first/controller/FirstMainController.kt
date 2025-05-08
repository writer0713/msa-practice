package com.writer0713.first.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/first-service")
@RestController
class FirstMainController {
    companion object {
        private val log = KotlinLogging.logger { }
    }

    @GetMapping
    fun main(): String = "first-service"

    @GetMapping("/hello")
    fun hello(): String = "hello! first-service"

    @GetMapping("/message")
    fun message(
        @RequestHeader("first-request") header: String,
    ): String {
        log.info { header }
        return "Hello World in First Service"
    }
}
