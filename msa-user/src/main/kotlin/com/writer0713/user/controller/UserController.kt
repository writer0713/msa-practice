package com.writer0713.user.controller

import com.writer0713.user.dto.Greeting
import com.writer0713.user.service.UserService
import com.writer0713.user.vo.RequestUser
import com.writer0713.user.vo.ResponseUser
import com.writer0713.user.vo.toDto
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class UserController(
    private val userService: UserService,
    private val env: Environment,
    private val greeting: Greeting,
) {
    companion object {
        private val log = KotlinLogging.logger { }
    }

    @PostMapping("/users")
    fun createUser(
        @Valid @RequestBody user: RequestUser,
    ): ResponseEntity<ResponseUser> {
        val userDto = user.toDto()
        val createdUserDto = userService.createUser(userDto)
        val responseUser = ResponseUser.fromDto(createdUserDto)
        return ResponseEntity.status(201).body(responseUser)
    }

    @GetMapping("/health-check")
    fun status(): String = "It's Working in User Service"

    @GetMapping("/welcome")
    fun welcome(): String {
        val messageFromEnv = env.getProperty("greeting.message") ?: "no greeting.message"
        log.info { ">>> messageFromEnv : $messageFromEnv" }

        return greeting.message
    }
}
