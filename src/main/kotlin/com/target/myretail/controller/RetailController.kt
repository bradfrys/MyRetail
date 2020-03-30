package com.target.myretail.controller

import com.target.myretail.service.ProductDetailsService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class RetailController(val productDetailsService: ProductDetailsService) {

    /**
     * Basic GET endpoint, accepts one ProductId as a path variable,
     * and returns the product's name and pricing information.
     */
    @GetMapping(path = ["/products/{id}"], produces = ["application/json"])
    fun getProductDetailsById(@PathVariable("id") productId: String): ResponseEntity<Any> {
        return ok().body(productDetailsService.hydrateProductDetails(productId))
    }

    //TODO trace PUT endpoint?

}
