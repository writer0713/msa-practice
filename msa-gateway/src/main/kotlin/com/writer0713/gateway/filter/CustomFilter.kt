package com.writer0713.gateway.filter

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomFilter : AbstractGatewayFilterFactory<CustomFilter.Config>(Config::class.java) {
    companion object {
        private val log = KotlinLogging.logger { }
    }

    override fun apply(config: Config): GatewayFilter =
        GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response

            log.info { "Custom PRE Filter : request id ${request.id}" }

            chain
                .filter(exchange)
                .then(
                    Mono.fromRunnable {
                        log.info { "Custom POST Filter : response code : ${response.statusCode}" }
                    },
                )
        }

    class Config {
        // put the configuration properties
    }
}
