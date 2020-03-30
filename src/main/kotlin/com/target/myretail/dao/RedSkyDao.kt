package com.target.myretail.dao

import org.springframework.stereotype.Component

@Component
class RedSkyDao {

    fun getProductNameById(productId: Int): String {
        return "name" //TODO add real redsky call
    }

}
