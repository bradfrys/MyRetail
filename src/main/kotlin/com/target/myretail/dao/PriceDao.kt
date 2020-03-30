package com.target.myretail.dao

import org.springframework.stereotype.Repository

@Repository
class PriceDao {

    fun getProductPriceById(productId: Int): Pair<Double, String> {
        return Pair(0.0, "USD") //TODO wire in real fake c* backend
    }

}
