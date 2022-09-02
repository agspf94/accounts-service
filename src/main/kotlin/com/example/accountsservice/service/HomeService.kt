package com.example.accountsservice.service

import com.example.accountsservice.constants.Constants.WELCOME
import org.springframework.stereotype.Service

@Service
class HomeService {
    fun welcome(): String {
        return WELCOME.message
    }
}
