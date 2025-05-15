package com.writer0713.user.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.writer0713.user.entity.UserEntity
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserDto(
    val email: String,
    val name: String,
    val password: String? = null,
    var encryptedPassword: String? = null,
    var userId: String = UUID.randomUUID().toString(),
    val createdAt: LocalDateTime? = null,
)

fun UserDto.toEntity(): UserEntity =
    UserEntity(
        email = this.email,
        name = this.name,
        userId = this.userId,
        encryptedPassword = this.encryptedPassword!!,
    )

fun UserDto.fromEntity(userEntity: UserEntity): UserDto =
    UserDto(
        email = userEntity.email,
        name = userEntity.name,
        userId = userEntity.userId,
        encryptedPassword = userEntity.encryptedPassword,
        createdAt = userEntity.createdAt,
    )
