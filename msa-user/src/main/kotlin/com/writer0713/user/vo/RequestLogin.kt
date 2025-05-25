package com.writer0713.user.vo

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class RequestLogin(
    @field:NotNull(message = "Email cannot be null")
    @field:Size(min = 2, message = "Email must be at least 2 characters")
    @field:Email
    val email: String,
    @field:NotNull(message = "Password cannot be null")
    @field:Size(min = 8, message = "Password must be at least 8 characters")
    val password: String,
)
