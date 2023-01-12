package com.example.flutterBlogApi.repository

import com.example.flutterBlogApi.domain.auth.AuthEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Transactional(readOnly = true)
interface AuthRepository  : JpaRepository<AuthEntity, Long> {
    fun findByRefreshToken(token: String): AuthEntity?
    fun findByKeyEmail(email: String) : AuthEntity?

    @Modifying
    @Query("delete from AuthEntity a where a.refreshToken = :token")
    fun deleteByRefreshToken(@Param("token") refreshToken: String?)
}