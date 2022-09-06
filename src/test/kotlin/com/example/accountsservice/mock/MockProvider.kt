package com.example.accountsservice.mock

import com.example.accountsservice.dto.request.AccountRequest
import com.example.accountsservice.entity.Account
import org.springframework.data.domain.PageRequest
import kotlin.random.Random

class MockProvider {
    companion object {
        fun getAccountRequest(): AccountRequest {
            return AccountRequest("Anderson", "1994-01-13")
        }

        fun getAccount(): Account {
            return Account(Random.nextLong(), "Anderson", "1994-01-13")
        }

        fun getPageable(): PageRequest {
            return PageRequest.of(0, 2)
        }

        fun getNewAccount(oldAccount: Account): Account {
            return Account(
                oldAccount.id,
                oldAccount.name + " - EDITED NAME",
                oldAccount.birthday + " - EDITED BIRTHDAY"
            )
        }
    }
}
