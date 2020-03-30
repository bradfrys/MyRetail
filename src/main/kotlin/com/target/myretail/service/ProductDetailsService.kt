package com.target.myretail.service

import com.target.myretail.dao.PriceDao
import com.target.myretail.dao.RedSkyDao
import com.target.myretail.model.Product
import org.springframework.stereotype.Service

@Service
class ProductDetailsService(val redSkyDao: RedSkyDao, val priceDao: PriceDao) {

    fun hydrateProductDetails(productId: String): Product? {
        val priceDetailRow = priceDao.getProductPriceAndCurrencyById(productId)
        val productName = redSkyDao.getProductNameById(productId)
        return if (priceDetailRow != null)
            Product(productId, productName, Pair(priceDetailRow.value, priceDetailRow.currencyCode))
        else
            Product(productId, productName, Pair(0.0, "Price not found."))
    }

}
