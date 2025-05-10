package com.writer0713.gateway.filter

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GlobalFilter : AbstractGatewayFilterFactory<GlobalFilter.Config>(Config::class.java) {
    companion object {
        private val log = KotlinLogging.logger { }
    }

    override fun apply(config: Config): GatewayFilter =
        GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response

            log.info { "Global Filter baseMessage : ${config.baseMessage}" }

            if (config.preLogger) {
                log.info { "Global Filter Start : request id -> ${request.id}" }
            }

            chain
                .filter(exchange)
                .then(
                    Mono.fromRunnable {
                        if (config.postLogger) {
                            log.info { "Global POST Filter : response code : ${response.statusCode}" }
                        }
                    },
                )
        }

    class Config(
        internal val baseMessage: String,
        internal val preLogger: Boolean,
        internal val postLogger: Boolean,
    )
}
