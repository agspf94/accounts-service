package com.example.accountsservice.service.accounts

import com.example.accountsservice.entity.Account
import com.example.accountsservice.mock.MockProvider.Companion.getAccount
import com.example.accountsservice.mock.MockProvider.Companion.getAccountRequest
import com.example.accountsservice.repository.AccountsRepository
import com.example.accountsservice.service.AccountsService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.Optional

@SpringBootTest
class AccountsServiceTest {
    @Autowired
    private lateinit var accountsService: AccountsService

    @MockBean
    private lateinit var accountsRepository: AccountsRepository

    @Test
    fun `accountsService should not be null`() {
        assertNotNull(accountsService)
    }

    @Test
    fun `createAccount should return the account created`() {
        val accountRequest = getAccountRequest()
        val accountToCreate = accountRequest.toAccount()
        val account = Account(1, accountToCreate.name, accountToCreate.birthday)

        `when`(accountsRepository.save(accountToCreate)).thenReturn(account)

        val createdAccount = accountsService.createAccount(accountRequest)

        assertEquals(accountToCreate.name, createdAccount.name)
        assertEquals(accountToCreate.birthday, createdAccount.birthday)
    }

    @Test
    fun `getAccount should return the account requested`() {
        val id = 1L
        val account = getAccount()

        `when`(accountsRepository.findById(id)).thenReturn(Optional.of(account))

        val accountReturned = accountsService.getAccount(id)

        assertEquals(account.id, accountReturned.id)
    }
}
