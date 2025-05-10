package com.writer0713.gateway.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder

// @Configuration
class FilterConfig {
    //    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        val routeLocator = builder.routes()

        // first-service
        routeLocator.route { r ->
            r
                .path("/first-service/**")
                .filters {
                    it
                        .addRequestHeader("first-request", "first-request-header")
                        .addResponseHeader("first-response", "first-response-header")
                }.uri("http://127.0.0.1:8081")
        }

        // second-service
        routeLocator.route { r ->
            r
                .path("/second-service/**")
                .filters {
                    it
                        .addRequestHeader("second-request", "second-request-header")
                        .addResponseHeader("second-response", "second-response-header")
                }.uri("http://127.0.0.1:8082")
        }

        return routeLocator.build()
    }
}
