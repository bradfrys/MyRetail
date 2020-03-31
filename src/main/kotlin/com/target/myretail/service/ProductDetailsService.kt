package com.target.myretail.service

import com.target.myretail.dao.PriceDao
import com.target.myretail.dao.RedSkyDao
import com.target.myretail.model.PriceRow
import com.target.myretail.model.Product
import com.target.myretail.model.StorePriceRequestBody
import org.slf4j.LoggerFactory.*
import org.springframework.stereotype.Service

/**
 * Service class for handling/aggregating products and product details.
 * Meant to be the logic/processing layer between the controller and data access objects.
 */
@Service
class ProductDetailsService(val redSkyDao: RedSkyDao, val priceDao: PriceDao) {

    private val log = getLogger(ProductDetailsService::class.java)

    /**
     * Calls cassandra for price data, then calls RedSky for product name,
     * and returns the aggregate information.
     */
    fun hydrateProductDetails(productId: String): Product {
        val priceDetailRow = priceDao.getProductPriceAndCurrencyById(productId)
        log.info("Retrieved price details for ID $productId: $priceDetailRow")

        val productName = redSkyDao.getProductNameById(productId)
        log.info("Retrieved product name for ID $productId: $productName")

        return Product(productId, productName, Pair(priceDetailRow.value, priceDetailRow.currencyCode))
    }

    /**
     * Stores price data to cassandra. Validation could be done here, or at the annotation
     * level in StorePriceRequestBody.kt or PriceRow.kt, and returns the information
     * that was inserted into the table.
     *
     * I debated calling the PriceDao.saveProductPrice() from the controller directly,
     * but stayed within my existing structure for consistency's sake.
     * There could be some hypothetical aggregation happening here.
     */
    fun storeProductPrice(productId: String, storePriceRequest: StorePriceRequestBody): PriceRow {
        val storedPriceRow = priceDao.saveProductPriceAndCurrencyForId(productId, storePriceRequest.value, storePriceRequest.currencyCode)
        log.info("Stored price details for ID $productId: $storedPriceRow")
        return storedPriceRow
    }

}
