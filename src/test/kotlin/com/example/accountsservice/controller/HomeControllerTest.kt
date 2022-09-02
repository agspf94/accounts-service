package com.example.accountsservice.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/*
Validate that the context is creating the controller
 */

@SpringBootTest
class HomeControllerTest {
    @Autowired
    private lateinit var homeController: HomeController

    @Test
    fun `Home Controller is not null`() {
        assertThat(homeController).isNotNull
    }
}
