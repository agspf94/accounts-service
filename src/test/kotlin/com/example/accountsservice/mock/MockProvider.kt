package com.example.accountsservice.mock

import com.example.accountsservice.dto.request.AccountRequest
import com.example.accountsservice.entity.Account

class MockProvider {
    companion object {
        fun getAccountRequest(): AccountRequest {
            return AccountRequest("Anderson", "1994-01-13")
        }

        fun getAccount(): Account {
            return Account(1, "Anderson", "1994-01-13")
        }
    }
}
