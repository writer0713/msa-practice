package com.writer0713.second

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class SecondServiceApplication

fun main(args: Array<String>) {
    runApplication<SecondServiceApplication>(*args)
}
