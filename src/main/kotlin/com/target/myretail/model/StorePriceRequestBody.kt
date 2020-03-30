package com.target.myretail.model

import com.fasterxml.jackson.annotation.JsonProperty

data class StorePriceRequestBody(@JsonProperty("value") val value: Double, @JsonProperty("currency_code") val currencyCode: String)
