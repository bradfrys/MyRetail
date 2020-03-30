package com.target.myretail.controller

import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler
    fun handleException(ex: Exception): ResponseEntity<Any> = status(INTERNAL_SERVER_ERROR).body(ex.message)

}
