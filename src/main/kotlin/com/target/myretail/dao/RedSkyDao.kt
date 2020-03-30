package com.target.myretail.dao

import org.springframework.stereotype.Component

@Component
class RedSkyDao {

    fun getProductNameById(productId: String): String {
        return "name" //TODO add real redsky call
    }

}
