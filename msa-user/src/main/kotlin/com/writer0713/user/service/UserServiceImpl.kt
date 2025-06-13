package com.writer0713.user.service

import com.writer0713.user.client.OrderServiceClient
import com.writer0713.user.dto.UserDto
import com.writer0713.user.dto.toEntity
import com.writer0713.user.entity.toDto
import com.writer0713.user.repository.UserRepository
import feign.FeignException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val orderServiceClient: OrderServiceClient,
    @Lazy private val passwordEncoder: BCryptPasswordEncoder,
) : UserService {
    companion object {
        private val log = KotlinLogging.logger {}
    }

    override fun createUser(userDto: UserDto): UserDto {
        // 비밀번호 암호화
        userDto.encryptedPassword = passwordEncoder.encode(userDto.password)

        val userEntity = userDto.toEntity()
        val savedEntity = userRepository.save(userEntity)

        return savedEntity.toDto()
    }

    override fun getUserById(userId: String): UserDto {
        val userEntity = userRepository.findByUserId(userId) ?: throw UsernameNotFoundException("User not found")
        val userDto = userEntity.toDto()

        try {
            val orders = orderServiceClient.getOrders(userId)
            userDto.orders = orders
        } catch (ex: FeignException) {
            log.error { ex.message }
        }

        return userDto
    }

    override fun getAllUsers(): Iterable<UserDto> =
        userRepository.findAll().map { userEntity ->
            userEntity.toDto()
        }

    override fun getUserDetailsByEmail(email: String): UserDto {
        val userEntity = userRepository.findByEmail(email) ?: throw UsernameNotFoundException(email)
        return userEntity.toDto()
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val userEntity = userRepository.findByEmail(username) ?: throw UsernameNotFoundException(username)

        return User(
            userEntity.email,
            userEntity.encryptedPassword,
            true,
            true,
            true,
            true,
            emptyList(),
        )
    }
}
