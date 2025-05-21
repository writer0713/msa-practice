package com.writer0713.user.entity

import com.writer0713.user.dto.UserDto
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false, unique = true)
    var email: String,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false, unique = true)
    var userId: String,
    @Column(nullable = false)
    var encryptedPassword: String,
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),
)

fun UserEntity.toDto(): UserDto =
    UserDto(
        email = this.email,
        name = this.name,
        userId = this.userId,
        encryptedPassword = this.encryptedPassword,
        createdAt = this.createdAt,
    )
