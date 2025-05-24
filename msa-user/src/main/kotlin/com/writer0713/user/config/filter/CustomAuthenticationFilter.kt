package com.writer0713.user.config.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.writer0713.user.service.UserService
import com.writer0713.user.vo.RequestLogin
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class CustomAuthenticationFilter(
    private val objectMapper: ObjectMapper,
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager,
) : UsernamePasswordAuthenticationFilter(authenticationManager) {
    companion object {
        private val log = KotlinLogging.logger { }
    }

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): Authentication {
        log.info { ">>> attemptAuthentication" }

        val credentials = objectMapper.readValue(request.inputStream, RequestLogin::class.java)
        val authenticationToken =
            UsernamePasswordAuthenticationToken(
                credentials.email,
                credentials.password,
            )

        return authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication,
    ) {
        val user = authResult.principal as User
        val email = user.username

        val userDto = userService.getUserDetailsByEmail(email)

        // TODO : userDto to jwt
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException,
    ) {
        super.unsuccessfulAuthentication(request, response, failed)
    }
}
