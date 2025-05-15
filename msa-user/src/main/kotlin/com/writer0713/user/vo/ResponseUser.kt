package com.writer0713.user.vo

import com.writer0713.user.dto.UserDto

data class ResponseUser(
    val email: String,
    val name: String,
    val userId: String,
) {
    companion object {
        fun fromDto(createdUserDto: UserDto): ResponseUser =
            ResponseUser(
                email = createdUserDto.email,
                name = createdUserDto.name,
                userId = createdUserDto.userId,
            )
    }
}
