package com.writer0713.user.repository

import com.writer0713.user.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long> {
    fun findByUserId(userId: String): UserEntity?
}
