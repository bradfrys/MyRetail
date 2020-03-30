package com.target.myretail.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * This is by far the worst design in this project. In a perfect world,
 * I would have a library to call on to map responses from an internal API.
 * To save time for a proof of concept, I crudely mapped out the direct JSON path
 * through the RedSky response json to the product title.
 */
data class RedSkyResponse(@JsonProperty("product") val product: RedSkyProduct)

data class RedSkyProduct(@JsonProperty("item") val item: Item)

data class Item(@JsonProperty("product_description") val description: Description)

data class Description(@JsonProperty("title") val productName: String)
