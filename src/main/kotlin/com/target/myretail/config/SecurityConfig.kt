package com.target.myretail.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.factory.PasswordEncoderFactories.*

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    companion object {
        const val DEVELOPER_ROLE = "developer"
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        val encoder = createDelegatingPasswordEncoder()
        auth.inMemoryAuthentication()
            .withUser("dev")
            .password(encoder.encode("supersafe"))
            .roles(DEVELOPER_ROLE)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .anyRequest().authenticated() // any request must be authetnicated
            .and().httpBasic() // and uses http basic auth
            .and().csrf().disable() // and cross site request forgery disabled
    }

}