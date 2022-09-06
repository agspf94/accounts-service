package com.example.accountsservice.controller

import com.example.accountsservice.dto.request.AccountRequest
import com.example.accountsservice.dto.response.DeleteResponse
import com.example.accountsservice.entity.Account
import com.example.accountsservice.service.AccountsService
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountsController(
    private val accountsService: AccountsService
) {
    @PostMapping("/create")
    fun createAccount(@RequestBody accountRequest: AccountRequest): Account {
        return accountsService.createAccount(accountRequest)
    }

    @GetMapping("/{id}")
    fun getAccount(@PathVariable id: Long): Account {
        return accountsService.getAccount(id)
    }

    @GetMapping("/all")
    fun getAllAccounts(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "2") size: Int
    ): List<Account> {
        return accountsService.getAllAccounts(PageRequest.of(page, size))
    }

    @PatchMapping("/edit/{id}")
    fun editAccount(@RequestBody account: Account): Account {
        return accountsService.editAccount(account)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteAccount(@PathVariable id: Long): DeleteResponse {
        return accountsService.deleteAccount(id)
    }
}
