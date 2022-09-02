package com.example.accountsservice.controller

import com.example.accountsservice.service.AccountsService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountsController(
    private val accountsService: AccountsService
) {
}
