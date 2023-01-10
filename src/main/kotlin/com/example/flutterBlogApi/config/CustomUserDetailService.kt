package com.example.flutterBlogApi.config

import com.example.flutterBlogApi.exception.UserNotFoundException
import com.example.flutterBlogApi.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class CustomUserDetailService (

    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return UserDetailsImpl(
            user = userRepository
                .findByEmail(email = username!!)
                ?: throw UsernameNotFoundException("존재하지 않는 username 입니다.")
        )
    }

}