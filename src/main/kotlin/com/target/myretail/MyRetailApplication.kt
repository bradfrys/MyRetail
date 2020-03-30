package com.target.myretail

import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [
	SecurityAutoConfiguration::class,
	UserDetailsServiceAutoConfiguration::class,
	ManagementWebSecurityAutoConfiguration::class])
class MyRetailApplication

fun main(args: Array<String>) {
	runApplication<MyRetailApplication>(*args)
}
