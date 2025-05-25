package com.writer0713.user.config.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class RequestValidationFilter : Filter {
    override fun doFilter(
        servletRequest: ServletRequest,
        servletResponse: ServletResponse,
        filterChain: FilterChain,
    ) {
        // 형변환 필요
        val httpRequest = servletRequest as HttpServletRequest
        val httpResponse = servletResponse as HttpServletResponse

        val requestId = httpRequest.getHeader("Request-Id")

        if (requestId.isNullOrBlank()) {
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST)
            return
        }

        filterChain.doFilter(servletRequest, servletResponse)
    }
}
