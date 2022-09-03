package com.example.accountsservice.repository

import com.example.accountsservice.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountsRepository : JpaRepository<Account, Long>
