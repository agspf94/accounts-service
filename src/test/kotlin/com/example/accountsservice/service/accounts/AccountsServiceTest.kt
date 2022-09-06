package com.example.accountsservice.service.accounts

import com.example.accountsservice.constant.Constants.Companion.DELETE_MESSAGE
import com.example.accountsservice.entity.Account
import com.example.accountsservice.exception.EmptyListException
import com.example.accountsservice.mock.MockProvider.Companion.getAccount
import com.example.accountsservice.mock.MockProvider.Companion.getAccountRequest
import com.example.accountsservice.mock.MockProvider.Companion.getNewAccount
import com.example.accountsservice.mock.MockProvider.Companion.getPageable
import com.example.accountsservice.repository.AccountsRepository
import com.example.accountsservice.service.AccountsService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.PageImpl
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

    @Test
    fun `getAccount should throw no such element exception when the account does not exist`() {
        val id = 1L

        `when`(accountsRepository.findById(id)).thenReturn(Optional.empty())

        assertThrows<NoSuchElementException> { accountsService.getAccount(id) }
    }

    @Test
    fun `getAllAccounts should return a list with all created accounts`() {
        val pageable = getPageable()
        val accountsList = listOf(getAccount(), getAccount())
        val accountsListPage = PageImpl(accountsList, pageable, accountsList.size.toLong())

        `when`(accountsRepository.findAll(pageable)).thenReturn(accountsListPage)

        val returnedAccountsList = accountsService.getAllAccounts(pageable)

        assertEquals(accountsList.size, returnedAccountsList.size)
        assertEquals(accountsList.first(), returnedAccountsList.first())
        assertEquals(accountsList.last(), returnedAccountsList.last())
    }

    @Test
    fun `getAllAccounts should throw empty list exception when there are no accounts created yet`() {
        val pageable = getPageable()
        val accountsList = listOf<Account>()
        val accountsListPage = PageImpl(accountsList, pageable, 0)

        `when`(accountsRepository.findAll(pageable)).thenReturn(accountsListPage)

        assertThrows<EmptyListException> { accountsService.getAllAccounts(pageable) }
    }

    @Test
    fun `editAccount should edit an account successfully`() {
        val oldAccount = getAccount()
        val newAccount = getNewAccount(oldAccount)

        `when`(accountsRepository.findById(newAccount.id)).thenReturn(Optional.of(oldAccount))
        `when`(accountsRepository.save(newAccount)).thenReturn(newAccount)

        val editedAccount = accountsService.editAccount(newAccount)

        assertEquals(newAccount.id, editedAccount.id)
        assertEquals(newAccount.name, editedAccount.name)
        assertEquals(newAccount.birthday, editedAccount.birthday)
    }

    @Test
    fun `editAccount should throw no such element exception when the account does not exist`() {
        val account = getAccount()

        `when`(accountsRepository.findById(account.id)).thenReturn(Optional.empty())

        assertThrows<NoSuchElementException> { accountsService.editAccount(account) }
    }

    @Test
    fun `deleteAccount should delete an account successfully`() {
        val id = 1L

        `when`(accountsRepository.findById(id)).thenReturn(Optional.of(getAccount()))
        doNothing().`when`(accountsRepository).deleteById(id)

        val response = accountsService.deleteAccount(id)

        assertEquals(id, response.id)
        assertEquals(DELETE_MESSAGE, response.message)
    }

    @Test
    fun `deleteAccount should throw no such element exception when the account does not exist`() {
        val id = 1L

        `when`(accountsRepository.findById(id)).thenReturn(Optional.empty())

        assertThrows<NoSuchElementException> { accountsService.deleteAccount(id) }
    }
}
