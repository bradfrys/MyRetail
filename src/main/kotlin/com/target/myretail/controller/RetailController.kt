package com.target.myretail.controller

import com.target.myretail.service.ProductDetailsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class RetailController(val productDetailsService: ProductDetailsService) {

    @GetMapping(path = ["/products/{id}"], produces = ["application/json"])
    fun getProductDetailsById(@PathVariable("id") productId: Int): ResponseEntity<Any> {
        return ResponseEntity.ok().body(productDetailsService.hydrateProductDetails(productId))
    }

    //TODO trace PUT endpoint?

}
