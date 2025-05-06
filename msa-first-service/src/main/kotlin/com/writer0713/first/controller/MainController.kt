package com.writer0713.first.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/first-service")
@RestController
class MainController {
    @GetMapping("/")
    fun main(): String = "first-service"

    @GetMapping("/hello")
    fun hello(): String = "hello! first-service"
}
