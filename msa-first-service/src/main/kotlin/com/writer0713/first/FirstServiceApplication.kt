package com.writer0713.msa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = ["com.writer0713.first"])
class FirstServiceApplication

fun main(args: Array<String>) {
    runApplication<FirstServiceApplication>(*args)
}
