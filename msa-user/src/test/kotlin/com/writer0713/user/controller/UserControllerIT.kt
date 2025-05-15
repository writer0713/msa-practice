package com.writer0713.user.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.writer0713.user.vo.RequestUser
import org.junit.jupiter.api.DisplayName
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Test

@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
class UserControllerIT(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
) {
    @DisplayName("UserController - createUser")
    @Test
    fun case_1() {
        // given
        val content =
            RequestUser(
                email = "test@naver.com",
                name = "test",
                password = "password!",
            ).let { objectMapper.writeValueAsString(it) }

        // when
        // then
        mockMvc
            .perform(
                post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content),
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(jsonPath("$.email").exists())
            .andExpect(jsonPath("$.name").exists())
            .andExpect(jsonPath("$.userId").exists())
            .andExpect(status().isCreated)
    }
}
