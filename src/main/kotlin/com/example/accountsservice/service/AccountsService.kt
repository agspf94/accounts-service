package com.example.accountsservice.service

import com.example.accountsservice.repository.AccountsRepository
import org.springframework.stereotype.Service

@Service
class AccountsService(
    private val accountsRepository: AccountsRepository
) {
}
