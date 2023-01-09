package com.example.flutterBlogApi.model

import com.example.flutterBlogApi.domain.Blog
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.Column


data class BlogRequest (
    val title: String,
    val content : String
)

data class BlogResponse (

    val id : Long,
    val title: String,
    val content : String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt : LocalDateTime?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime?,
) {

    companion object {
        operator fun invoke(blog : Blog) =
            with(blog) {
                BlogResponse(
                    id = id!!,
                    title = title,
                    content = content,
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                )
            }
    }

}

