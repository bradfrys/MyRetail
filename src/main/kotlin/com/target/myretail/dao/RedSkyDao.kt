package com.target.myretail.dao

import com.target.myretail.model.RedSkyResponse
import org.springframework.http.RequestEntity.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.util.UriTemplate

/**
 * Data Access for RedSky product information. Normally,
 * I would expect this to be an independent service (likely in a lib), but here
 * it's acting as a logical data access object, so I'm treating it as such.
 * It could be any kind of back-end for data storage, ie another c* table.
 *
 * Presumably, the data that's being retrieved has already been validated,
 * (either before being stored initially, or by the RedSky service)
 * otherwise, this class or RedSkyResponse.kt would be the place to include validation.
 */
@Component
class RedSkyDao(val httpClient: RestTemplate) {

    private val productDetailsUrl: UriTemplate = UriTemplate("https://redsky.target.com/v2/pdp/tcin/{productId}")

    fun getProductNameById(productId: String): String {
        val request = get(productDetailsUrl.expand(productId)).build()
        val response = httpClient.exchange<RedSkyResponse>(request)
        return response.body!!.product.item.description.productName
    }

}
