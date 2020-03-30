package com.target.myretail.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * In it's own service, I would have preferred to take in the
 * ProductId to be saved as a field within this request body,
 * instead of having it hanging out as a path variable. That way,
 * the body can map easily to the PriceRow saved in cassandra.
 */
data class StorePriceRequestBody(@JsonProperty("value") val value: Double, @JsonProperty("currency_code") val currencyCode: String)
