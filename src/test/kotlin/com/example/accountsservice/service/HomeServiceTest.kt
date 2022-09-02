package com.example.accountsservice.service

import com.example.accountsservice.constants.Constants.WELCOME
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HomeServiceTest {
    @Autowired
    private lateinit var homeService: HomeService

    @Test
    fun `welcome should return the correct string`() {
        Assertions.assertEquals(WELCOME.message, homeService.welcome())
    }
}
