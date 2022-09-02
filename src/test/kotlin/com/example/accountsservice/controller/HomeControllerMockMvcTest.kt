package com.example.accountsservice.controller

import com.example.accountsservice.constants.Constants.WELCOME
import com.example.accountsservice.service.HomeService
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/*
The same as the Rest Template but does not start the server. It loads the full context
 */

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerMockMvcTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var homeService: HomeService

    @Test
    fun `Home Controller getHome should return the correct string`() {
        `when`(homeService.welcome()).thenReturn(WELCOME.message)

        mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("Welcome to DevPath. This is the accounts-service")))
    }
}
