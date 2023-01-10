package com.example.flutterBlogApi.config.jwt

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider
) {

    @Bean
    fun filterChain (http : HttpSecurity) : SecurityFilterChain {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/v1/users/signup").permitAll()
            .antMatchers("/api/v1/users/signin").permitAll()
            .antMatchers("/api/v1/users/refresh").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
}