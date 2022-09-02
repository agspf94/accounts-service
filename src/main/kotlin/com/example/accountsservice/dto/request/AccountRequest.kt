package com.example.accountsservice.dto.request

import com.example.accountsservice.entity.Account

data class AccountRequest(
    val name: String,
    val birthday: String
) {
    fun toAccount(): Account {
        return Account(
            id = 0,
            name = name,
            birthday = birthday
        )
    }
}
