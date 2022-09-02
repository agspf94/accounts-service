package com.example.accountsservice.controller

import com.example.accountsservice.dto.request.AccountRequest
import com.example.accountsservice.entity.Account
import com.example.accountsservice.service.AccountsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountsController(
    private val accountsService: AccountsService
) {
    @PostMapping("/create")
    fun create(@RequestBody accountRequest: AccountRequest): Account {
        return accountsService.create(accountRequest)
    }
}
