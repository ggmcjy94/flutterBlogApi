package com.example.flutterBlogApi.controller

import com.example.flutterBlogApi.model.*
import com.example.flutterBlogApi.service.AuthService
import com.example.flutterBlogApi.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
    private val authService : AuthService,
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody request: SignUpRequest): ResponseEntity<Unit> {
        userService.signUp(request)
        return ResponseEntity<Unit>(HttpStatus.CREATED)
    }



    @PostMapping("/signin")
    fun signIn(@RequestBody request: SignInRequest) : ResponseEntity<SignInResponse> {
        return ResponseEntity<SignInResponse>(userService.signIn(request), HttpStatus.OK);
    }

    @PostMapping("/logout")
    fun logout() {
        return userService.logout();
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshTokenRequest) : RefreshTokenResponse  {
        return authService.reGeneratedAccessToken(request.refreshToken)
    }

}