package com.target.myretail.model

import org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table

@Table("price_table")
data class PriceRow(@PrimaryKeyColumn("product_id", type = PARTITIONED) val productId: String,
                    @Column("value") val value: Double,
                    @Column("currency_code") val currencyCode: String)
