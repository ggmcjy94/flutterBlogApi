package com.example.flutterBlogApi.model



data class SignUpRequest (
    val email: String,
    val password:String,
    val username:String,
)


data class SignInRequest(
    val email: String,
    val password: String,
)


data class SignInResponse (
    val email: String,
    val username: String,
    val accessToken : String,
    val refreshToken: String,
)

data class RefreshTokenRequest (
    val refreshToken: String?,
)

data class RefreshTokenResponse (
    val accessToken: String?,
)