package com.example.flutterBlogApi.controller

import com.example.flutterBlogApi.model.BlogRequest
import com.example.flutterBlogApi.model.BlogResponse
import com.example.flutterBlogApi.service.BlogService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/vi/blogs")
class BlogController (
    private val blogService: BlogService,
) {

    @PostMapping
    fun postBlog (
        @RequestBody request : BlogRequest
    ) : ResponseEntity<BlogResponse> {
        return ResponseEntity(blogService.create(request),HttpStatus.CREATED);
    }


    @GetMapping
    fun getAllBlog() : ResponseEntity<List<BlogResponse>> =
        ResponseEntity(blogService.getAll(), HttpStatus.OK);

}