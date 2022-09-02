package com.example.accountsservice.repository

import com.example.accountsservice.entity.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountsRepository : CrudRepository<Account, Long>
