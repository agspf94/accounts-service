package com.example.accountsservice.service

import com.example.accountsservice.constant.Constants.Companion.DELETE_MESSAGE
import com.example.accountsservice.constant.Constants.Companion.EMPTY_LIST
import com.example.accountsservice.constant.Constants.Companion.NOT_FOUND
import com.example.accountsservice.dto.request.AccountRequest
import com.example.accountsservice.dto.response.DeleteResponse
import com.example.accountsservice.entity.Account
import com.example.accountsservice.exception.EmptyListException
import com.example.accountsservice.repository.AccountsRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class AccountsService(
    private val accountsRepository: AccountsRepository
) {
    fun createAccount(accountRequest: AccountRequest): Account {
        return accountsRepository.save(accountRequest.toAccount())
    }

    fun getAccount(id: Long): Account {
        return accountsRepository.findById(id)
            .map { it }
            .orElseThrow { NoSuchElementException(NOT_FOUND) }
    }

    fun getAllAccounts(pageable: Pageable): List<Account> {
        return accountsRepository.findAll(pageable)
            .stream()
            .collect(Collectors.toList())
            .ifEmpty { throw EmptyListException(EMPTY_LIST) }
    }

    fun editAccount(account: Account): Account {
        return accountsRepository.findById(account.id)
            .map {
                accountsRepository.save(
                    Account(
                        account.id,
                        account.name ?: it.name,
                        account.birthday ?: it.birthday
                    )
                )
            }
            .orElseThrow { NoSuchElementException(NOT_FOUND) }
    }

    fun deleteAccount(id: Long): DeleteResponse {
        return accountsRepository.findById(id)
            .map {
                accountsRepository.deleteById(id)
                DeleteResponse(id, DELETE_MESSAGE)
            }
            .orElseThrow { NoSuchElementException(NOT_FOUND) }
    }
}
