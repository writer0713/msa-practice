package com.writer0713.second.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {
    @GetMapping("/")
    fun main(): String = "second-service"

    @GetMapping("/hello")
    fun hello(): String = "hello! second-service"
}
