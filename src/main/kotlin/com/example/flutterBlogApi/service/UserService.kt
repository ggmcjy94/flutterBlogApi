package com.example.flutterBlogApi.service

import com.example.flutterBlogApi.config.jwt.JWTProperties
import com.example.flutterBlogApi.config.jwt.JwtTokenProvider
import com.example.flutterBlogApi.domain.User
import com.example.flutterBlogApi.domain.auth.AuthEntity
import com.example.flutterBlogApi.exception.PasswordNotMatchedException
import com.example.flutterBlogApi.exception.UserExistsException
import com.example.flutterBlogApi.exception.UserNotFoundException
import com.example.flutterBlogApi.model.SignInRequest
import com.example.flutterBlogApi.model.SignInResponse
import com.example.flutterBlogApi.model.SignUpRequest
import com.example.flutterBlogApi.repository.AuthRepository
import com.example.flutterBlogApi.repository.UserRepository
import com.example.flutterBlogApi.utils.BCryptUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService (
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val jwtTokenProvider: JwtTokenProvider,
){




    @Transactional
    fun signUp(signUpRequest: SignUpRequest) {
        with(signUpRequest) {
            userRepository.findByEmail(email) ?. let {
                throw UserExistsException()
            }
            val user = User(
                email = email,
                password = BCryptUtils.hash(password),
                username = username,
            )
            userRepository.save(user)
        }
    }

    @Transactional
    fun signIn(request: SignInRequest): SignInResponse {
        return with(userRepository.findByEmail(request.email) ?: throw UserNotFoundException()) {
            val verified = BCryptUtils.verify(request.password, password)
            if (!verified) {
                throw PasswordNotMatchedException()
            }
            val token =  jwtTokenProvider.createAccessToken(email)
            val createRefreshToken = jwtTokenProvider.createRefreshToken()

            println("re token :  $createRefreshToken")
            val auth = authRepository.findByKeyEmail(email)
            if (auth != null) {
                auth.updateRefreshToken(createRefreshToken);
            } else {
                authRepository.save(AuthEntity(refreshToken = createRefreshToken , keyEmail = email))
            }

            SignInResponse (
                email = email,
                username= username,
                accessToken = token,
                refreshToken = createRefreshToken,
            )
        }
    }
}