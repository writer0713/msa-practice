package com.writer0713.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@EnableConfigServer
@SpringBootApplication
class MsaConfigApplication

fun main(args: Array<String>) {
    runApplication<MsaConfigApplication>(*args)
}
