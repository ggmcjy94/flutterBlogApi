package com.example.flutterBlogApi.repository

import com.example.flutterBlogApi.domain.Blog
import org.springframework.data.jpa.repository.JpaRepository


interface BlogRepository  : JpaRepository<Blog, Long>{
    fun findAllByOrderByCreatedAtDesc(): List<Blog>?
}