package com.example.accountsservice.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Account(
    @Id
    @GeneratedValue
    val id: Long,
    val name: String?,
    val birthday: String?
)
