package com.target.myretail.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext
import org.springframework.web.client.RestTemplate

@Configuration
class SpringBeanConfig {

    // Default bean definition for Cassandra Mapping, for PriceDao.kt
    @Bean fun cassandraMapping(): CassandraMappingContext = BasicCassandraMappingContext()

    // Default bean definition for Rest Template, for RedSkyDao.kt
    @Bean fun restTemplate(): RestTemplate = RestTemplate()

}
