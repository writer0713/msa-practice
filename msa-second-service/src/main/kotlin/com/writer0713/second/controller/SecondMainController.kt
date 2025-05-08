package com.writer0713.second.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/second-service")
@RestController
class SecondMainController {
    companion object {
        private val log = KotlinLogging.logger { }
    }

    @GetMapping
    fun main(): String = "second-service"

    @GetMapping("/hello")
    fun hello(): String = "hello! second-service"

    @GetMapping("/message")
    fun message(
        @RequestHeader("second-request") header: String,
    ): String {
        log.info { header }
        return "Hello World in Second Service"
    }
}
