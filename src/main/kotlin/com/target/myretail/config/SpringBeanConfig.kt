package com.target.myretail.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext

@Configuration
class SpringBeanConfig {

    @Bean
    fun cassandraMapping(): CassandraMappingContext = BasicCassandraMappingContext()

}