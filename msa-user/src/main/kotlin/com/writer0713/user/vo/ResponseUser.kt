package com.writer0713.user.vo

import com.fasterxml.jackson.annotation.JsonInclude
import com.writer0713.user.dto.UserDto

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseUser(
    val email: String,
    val name: String,
    val userId: String,
    val orders: List<ResponseOrder> = emptyList(),
) {
    companion object {
        fun fromDto(createdUserDto: UserDto): ResponseUser =
            ResponseUser(
                email = createdUserDto.email,
                name = createdUserDto.name,
                userId = createdUserDto.userId,
                orders = createdUserDto.orders,
            )
    }
}
