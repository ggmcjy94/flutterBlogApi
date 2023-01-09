package com.example.flutterBlogApi.repository

import com.example.flutterBlogApi.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String) : User?
}