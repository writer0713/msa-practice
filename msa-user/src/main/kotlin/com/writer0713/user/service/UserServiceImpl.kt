package com.writer0713.user.service

import com.writer0713.user.dto.UserDto
import com.writer0713.user.dto.fromEntity
import com.writer0713.user.dto.toEntity
import com.writer0713.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {
    override fun createUser(userDto: UserDto): UserDto {
        val userEntity = userDto.toEntity()
        val savedEntity = userRepository.save(userEntity)

        return userDto.fromEntity(savedEntity)
    }
}
