package com.writer0713.user.service

import com.writer0713.user.dto.UserDto

interface UserService {
    fun createUser(userDto: UserDto): UserDto

    fun getUserById(userId: String): UserDto

    fun getAllUsers(): Iterable<UserDto>
}
