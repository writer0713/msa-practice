package com.writer0713.user.service

import com.writer0713.user.dto.UserDto
import com.writer0713.user.dto.fromEntity
import com.writer0713.user.dto.of
import com.writer0713.user.dto.toEntity
import com.writer0713.user.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
) : UserService {
    override fun createUser(userDto: UserDto): UserDto {
        // 비밀번호 암호화
        userDto.encryptedPassword = passwordEncoder.encode(userDto.password)

        val userEntity = userDto.toEntity()
        val savedEntity = userRepository.save(userEntity)

        return userDto.fromEntity(savedEntity)
    }

    override fun getUserById(userId: String): UserDto {
        val userEntity = userRepository.findByUserId(userId) ?: throw UsernameNotFoundException("User not found")
        val userDto = UserDto.of(userEntity)

        // TODO : 추후 orders set

        return userDto
    }

    override fun getAllUsers(): Iterable<UserDto> =
        userRepository.findAll().map { userEntity ->
            UserDto.of(userEntity)
        }
}
