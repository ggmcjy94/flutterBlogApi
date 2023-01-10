package com.example.flutterBlogApi.controller

import com.example.flutterBlogApi.model.*
import com.example.flutterBlogApi.service.AuthService
import com.example.flutterBlogApi.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
    private val authService : AuthService,
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody request: SignUpRequest) =
        userService.signUp(request)


    @PostMapping("/signin")
    fun signIn(@RequestBody request: SignInRequest) : SignInResponse {
        return userService.signIn(request)
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshTokenRequest) : RefreshTokenResponse  {
        return authService.reGeneratedAccessToken(request.refreshToken)
    }

}