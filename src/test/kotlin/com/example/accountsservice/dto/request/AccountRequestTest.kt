package com.example.accountsservice.dto.request

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AccountRequestTest {
    @Test
    fun `toAccount should return the correct Account`() {
        val accountRequest = AccountRequest("Anderson", "1994-01-13")
        val account = accountRequest.toAccount()

        assertEquals(accountRequest.name, account.name)
        assertEquals(accountRequest.birthday, account.birthday)
    }
}
