package com.example.flutterBlogApi.service

import com.example.flutterBlogApi.domain.Blog
import com.example.flutterBlogApi.model.BlogRequest
import com.example.flutterBlogApi.model.BlogResponse
import com.example.flutterBlogApi.repository.BlogRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BlogService (
    private val blogRepository: BlogRepository
) {

    @Transactional
    fun create(request: BlogRequest): BlogResponse {
        val blog = Blog(
            title = request.title,
            content = request.content
        )
        return BlogResponse(blogRepository.save(blog))
    }

    @Transactional(readOnly = true)
    fun getAll(): List<BlogResponse>? {
        return blogRepository.findAllByOrderByCreatedAtDesc()?.map {
            BlogResponse(it)
        }
    }


}