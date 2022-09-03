package com.example.accountsservice.service.home

import com.example.accountsservice.constant.Constants.Companion.WELCOME
import com.example.accountsservice.service.HomeService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HomeServiceTest {
    @Autowired
    private lateinit var homeService: HomeService

    @Test
    fun `Home Service should be not null`() {
        assertThat(homeService).isNotNull
    }

    @Test
    fun `welcome should return the correct string`() {
        assertEquals(WELCOME, homeService.welcome())
    }
}
