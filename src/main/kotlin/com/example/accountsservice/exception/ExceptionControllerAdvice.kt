package com.example.accountsservice.exception

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {
    @ExceptionHandler
    fun handleNotSuchElementException(e: NoSuchElementException): ResponseEntity<ErrorMessage> {
        return ResponseEntity(ErrorMessage(e.message), NOT_FOUND)
    }

    @ExceptionHandler
    fun handleEmptyListException(e: EmptyListException): ResponseEntity<ErrorMessage> {
        return ResponseEntity(ErrorMessage(e.message), OK)
    }
}
