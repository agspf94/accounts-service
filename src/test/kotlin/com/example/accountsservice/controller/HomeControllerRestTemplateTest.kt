package com.example.accountsservice.controller

import com.example.accountsservice.constants.Constants.WELCOME
import com.example.accountsservice.service.HomeService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort

/*
Start the application, listen for a connection and send the HTTP request
 */

@SpringBootTest(webEnvironment = RANDOM_PORT)
class HomeControllerRestTemplateTest {
    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @LocalServerPort
    private val port = 0

    @MockBean
    private lateinit var homeService: HomeService

    @Test
    fun `Home Controller getHome should return the correct string`() {
        `when`(homeService.welcome()).thenReturn(WELCOME.message)

        assertThat(restTemplate.getForObject("http://localhost:$port/", String::class.java)).contains("Welcome to DevPath. This is the accounts-service")
    }
}
