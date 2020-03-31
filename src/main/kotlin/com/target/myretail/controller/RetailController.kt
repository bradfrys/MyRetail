package com.target.myretail.controller

import com.target.myretail.model.StorePriceRequestBody
import com.target.myretail.service.ProductDetailsService
import org.slf4j.LoggerFactory.*
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*

@RestController
class RetailController(val productDetailsService: ProductDetailsService) {

    private val log = getLogger(RetailController::class.java)

    /**
     * Basic GET endpoint, accepts one ProductId as a path variable,
     * and returns the product's name and pricing information.
     */
    @GetMapping(path = ["/products/{id}"], produces = ["application/json"])
    fun getProductDetailsById(@PathVariable("id") productId: String): ResponseEntity<Any> {
        log.info("Product details requested for ID: $productId")
        return ok().body(productDetailsService.hydrateProductDetails(productId))
    }

    /**
     * Basic PUT endpoint, accepts one ProductId as a path variable,
     * and pricing information in a request body. Something this specific
     * would feel more at home in a service dedicated to pricing information storage,
     * but I included it here for the demo bonus. See StorePriceRequestBody.kt
     */
    @PutMapping(path = ["/products/{id}"], produces = ["application/json"])
    fun saveProductPrice(@PathVariable("id") productId: String,
                         @RequestBody storePriceRequest: StorePriceRequestBody): ResponseEntity<Any> {
        log.info("Price update requested for ID: $productId")
        return ok().body(productDetailsService.storeProductPrice(productId, storePriceRequest))
    }

}
