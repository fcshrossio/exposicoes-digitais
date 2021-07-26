package com.rossio.exhibitions.config

import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
class KeycloakSecurityConfig : KeycloakWebSecurityConfigurerAdapter() {
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        super.configure(http)
        http.authorizeRequests()
            .anyRequest()
            .permitAll()
        http.csrf().disable()
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        val keycloakAuthenticationProvider = keycloakAuthenticationProvider()
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(SimpleAuthorityMapper())
        auth.authenticationProvider(keycloakAuthenticationProvider)
    }

    @Bean
    override fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy? {
        return RegisterSessionAuthenticationStrategy(SessionRegistryImpl())
    }

    @Bean
    fun KeycloakConfigResolver(): KeycloakConfigResolver? {
        return KeycloakSpringBootConfigResolver()
    }

}