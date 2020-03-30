package com.target.myretail.service

import com.target.myretail.dao.PriceDao
import com.target.myretail.dao.RedSkyDao
import com.target.myretail.model.PriceRow
import com.target.myretail.model.Product
import com.target.myretail.model.StorePriceRequestBody
import org.springframework.stereotype.Service

/**
 * Service class for handling/aggregating products and product details.
 * Meant to be the logic/processing layer between the controller and data access objects.
 */
@Service
class ProductDetailsService(val redSkyDao: RedSkyDao, val priceDao: PriceDao) {

    /**
     * Calls cassandra for price data, then calls RedSky for product name,
     * and returns the aggregate information.
     */
    fun hydrateProductDetails(productId: String): Product {
        val priceDetailRow = priceDao.getProductPriceAndCurrencyById(productId)
        val productName = redSkyDao.getProductNameById(productId)
        return if (priceDetailRow != null)
            Product(productId, productName, Pair(priceDetailRow.value, priceDetailRow.currencyCode))
        else
            Product(productId, productName, Pair(0.0, "Price not found."))
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
        return priceDao.saveProductPriceAndCurrencyForId(productId, storePriceRequest.value, storePriceRequest.currencyCode)
    }

}
