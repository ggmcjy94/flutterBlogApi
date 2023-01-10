package com.example.flutterBlogApi.repository

import com.example.flutterBlogApi.domain.auth.AuthEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface AuthRepository  : JpaRepository<AuthEntity, Long> {
    fun findByRefreshToken(token: String): AuthEntity?
}