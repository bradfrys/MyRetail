package com.target.myretail.service

import com.target.myretail.dao.PriceDao
import com.target.myretail.dao.RedSkyDao
import com.target.myretail.model.Product
import org.springframework.stereotype.Service

@Service
class ProductDetailsService(val redSkyDao: RedSkyDao, val priceDao: PriceDao) {

    fun hydrateProductDetails(productId: Int): Product? { //TODO async?
        return Product(productId, redSkyDao.getProductNameById(productId), priceDao.getProductPriceById(productId))
    }

}
