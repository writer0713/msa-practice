package com.writer0713.first.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {
    @GetMapping("/")
    fun main(): String = "first-service"

    @GetMapping("/hello")
    fun hello(): String = "hello! first-service"
}
