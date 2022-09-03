package com.example.accountsservice.service

import com.example.accountsservice.constant.Constants.Companion.WELCOME
import org.springframework.stereotype.Service

@Service
class HomeService {
    fun welcome(): String {
        return WELCOME
    }
}
