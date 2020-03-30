package com.target.myretail.controller

import com.target.myretail.model.StorePriceRequestBody
import com.target.myretail.service.ProductDetailsService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*

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

    @PutMapping(path = ["/products/{id}"], produces = ["application/json"])
    fun saveProductPrice(@PathVariable("id") productId: String,
                         @RequestBody storePriceRequest: StorePriceRequestBody): ResponseEntity<Any> {
        return ok().body(productDetailsService.storeProductPrice(productId, storePriceRequest))
    }

}
