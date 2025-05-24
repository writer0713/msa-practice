package com.writer0713.user.service

import com.writer0713.user.dto.UserDto
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService : UserDetailsService {
    fun createUser(userDto: UserDto): UserDto

    fun getUserById(userId: String): UserDto

    fun getAllUsers(): Iterable<UserDto>

    fun getUserDetailsByEmail(email: String): UserDto
}
