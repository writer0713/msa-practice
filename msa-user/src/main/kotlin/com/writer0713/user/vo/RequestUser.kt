package com.writer0713.user.vo

import com.writer0713.user.dto.UserDto
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class RequestUser(
    @field:NotNull(message = "Email cannot be null")
    @field:Size(min = 2, message = "Email must be at least 2 characters")
    @field:Email
    val email: String,
    @field:NotNull(message = "Name cannot be null")
    @field:Size(min = 2, message = "Name must be at least 2 characters")
    val name: String,
    @field:NotNull(message = "Password cannot be null")
    @field:Size(min = 4, message = "Password must be at least 4 characters")
    val password: String,
)

fun RequestUser.toDto(): UserDto =
    UserDto(
        email = this.email,
        name = this.name,
        password = this.password,
    )
