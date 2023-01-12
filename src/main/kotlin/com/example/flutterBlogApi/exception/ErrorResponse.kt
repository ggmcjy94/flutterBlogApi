package com.example.flutterBlogApi.exception

data class ErrorResponse (
    val code : Int,
    val message : String,
) {
}