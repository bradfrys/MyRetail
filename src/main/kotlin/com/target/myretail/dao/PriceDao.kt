package com.target.myretail.dao

import com.target.myretail.model.PriceRow
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.data.cassandra.core.query.Query.query
import org.springframework.data.cassandra.core.query.where
import org.springframework.data.cassandra.core.selectOne
import org.springframework.stereotype.Repository

/**
 * Data Access Object for price data in Cassandra. Supports simple
 * select by product_id query for the price table.
 */
@Repository
class PriceDao(private val database: CassandraTemplate) {

    fun getProductPriceAndCurrencyById(productId: String): PriceRow =
        database.selectOne(query(where("product_id").`is`(productId)))
            ?: PriceRow(productId, 0.0, "Price not found.")

    fun saveProductPriceAndCurrencyForId(productId: String, value: Double, currencyCode: String): PriceRow =
        database.insert(PriceRow(productId, value, currencyCode))

}
