package com.writer0713.user.config.filter

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

class AuthenticationLoggingFilter : OncePerRequestFilter() {
    companion object {
        private val log = KotlinLogging.logger {}
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val requestId = request.getHeader("Request-Id")
        log.info { "[AuthenticationLoggingFilter] Successfully authenticated request with id : $requestId" }
        filterChain.doFilter(request, response)
    }
}
