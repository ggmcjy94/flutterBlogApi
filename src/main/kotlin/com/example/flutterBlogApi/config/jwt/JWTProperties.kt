package com.example.flutterBlogApi.config.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
data class JWTProperties(
    val issuer : String,
    val subject: String,
    val expiresTime: Long,
    val reExpiresTime: Long,
    val secret : String,
) {
}