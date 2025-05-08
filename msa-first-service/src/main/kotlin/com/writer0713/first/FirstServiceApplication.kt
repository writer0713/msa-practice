package com.writer0713.first

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class FirstServiceApplication

fun main(args: Array<String>) {
    runApplication<FirstServiceApplication>(*args)
}
