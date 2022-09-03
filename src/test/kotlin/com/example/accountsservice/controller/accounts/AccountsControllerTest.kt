package com.example.accountsservice.controller.accounts

import com.example.accountsservice.controller.AccountsController
import com.example.accountsservice.dto.request.AccountRequest
import com.example.accountsservice.service.AccountsService
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(AccountsController::class)
class AccountsControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var accountsController: AccountsController

    @MockBean
    private lateinit var accountsService: AccountsService

    @Test
    fun `Accounts Controller should not be null`() {
        assertNotNull(accountsController)
    }

    @Test
    fun `create should create an account successfully`() {
        val accountRequest = AccountRequest("Anderson","1994-01-13")
        val account = accountRequest.toAccount()

        `when`(accountsService.createAccount(accountRequest)).thenReturn(account)

        mockMvc.perform(
                post("/accounts/create").accept(APPLICATION_JSON)
                    .content("{\"name\":\"Anderson\",\"birthday\":\"1994-01-13\"}")
                    .contentType(APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().json("{\"id\":0,\"name\":\"Anderson\",\"birthday\":\"1994-01-13\"}"))
    }
}
