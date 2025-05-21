package com.writer0713.catalog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class CatalogApplication

fun main(args: Array<String>) {
    runApplication<CatalogApplication>(*args)
}
