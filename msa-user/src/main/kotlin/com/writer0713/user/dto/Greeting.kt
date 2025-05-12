package com.writer0713.user.dto

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class Greeting(
    @Value("\${greeting.message}")
    val message: String,
)
