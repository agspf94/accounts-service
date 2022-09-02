package com.example.accountsservice.service

import com.example.accountsservice.dto.request.AccountRequest
import com.example.accountsservice.entity.Account
import com.example.accountsservice.repository.AccountsRepository
import org.springframework.stereotype.Service

@Service
class AccountsService(
    private val accountsRepository: AccountsRepository
) {
    fun create(accountRequest: AccountRequest): Account {
        return accountsRepository.save(accountRequest.toAccount())
    }
}
