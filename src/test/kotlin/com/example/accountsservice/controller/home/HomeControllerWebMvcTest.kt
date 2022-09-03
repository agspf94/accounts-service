package com.example.accountsservice.controller.home

import com.example.accountsservice.constant.Constants.Companion.WELCOME
import com.example.accountsservice.controller.HomeController
import com.example.accountsservice.service.HomeService
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/*
The same as the MockMvc but now only the web layer is loaded instead of the full context
 */

@WebMvcTest(HomeController::class)
class HomeControllerWebMvcTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var homeService: HomeService

    @Test
    fun `welcome should return the correct string`() {
        `when`(homeService.welcome()).thenReturn(WELCOME)

        mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().string(containsString(WELCOME)))
    }
}
