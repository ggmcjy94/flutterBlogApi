package com.example.flutterBlogApi.service

import com.example.flutterBlogApi.config.jwt.JwtTokenProvider
import com.example.flutterBlogApi.exception.InvalidJwtTokenException
import com.example.flutterBlogApi.model.RefreshTokenRequest
import com.example.flutterBlogApi.model.RefreshTokenResponse
import com.example.flutterBlogApi.repository.AuthRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
class AuthService (
    val authRepository: AuthRepository,
    val jwtTokenProvider: JwtTokenProvider
) {

    fun reGeneratedAccessToken(refreshToken: String?): RefreshTokenResponse {
        // 1 token access refresh 를 줬음
        // 2 access 가 만료 됐음
        // 3 refresh 확인
        with(authRepository.findByRefreshToken(refreshToken!!) ?: throw InvalidJwtTokenException()) {
            if (jwtTokenProvider.isValidRefreshToken(refreshToken)) {
                return RefreshTokenResponse(accessToken = jwtTokenProvider.createAccessToken(this.keyEmail))
            }
            return RefreshTokenResponse(null)
        }
    }
}