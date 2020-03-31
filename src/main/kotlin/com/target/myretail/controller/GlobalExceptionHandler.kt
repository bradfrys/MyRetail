package com.target.myretail.controller

import org.slf4j.LoggerFactory.*
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException
import java.lang.Exception

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler
    fun handleException(ex: HttpClientErrorException): ResponseEntity<Any> {
        log.error("Error during HTTP exchange", ex)
        return status(INTERNAL_SERVER_ERROR).body(ex.message)
    }

    @ExceptionHandler
    fun handleException(ex: Exception): ResponseEntity<Any> {
        log.error("An error has occurred", ex)
        return status(INTERNAL_SERVER_ERROR).body(ex.message)
    }

}
