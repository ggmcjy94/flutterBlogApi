package com.example.flutterBlogApi.controller

import com.example.flutterBlogApi.model.SignInRequest
import com.example.flutterBlogApi.model.SignInResponse
import com.example.flutterBlogApi.model.SignUpRequest
import com.example.flutterBlogApi.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody request: SignUpRequest) =
        userService.signUp(request)


    @PostMapping("/signin")
    fun signIn(@RequestBody request: SignInRequest) : SignInResponse {
        return userService.signIn(request)
    }

}