package com.example.flutterBlogApi.config.jwt

import com.example.flutterBlogApi.exception.InvalidJwtTokenException
import com.example.flutterBlogApi.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.RuntimeException
import java.rmi.ServerException
import java.util.logging.Logger
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TokenVerifyInterceptor (
    private val userRepository: UserRepository,
): HandlerInterceptor {

    private val log = LoggerFactory.getLogger(javaClass)


    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val method = request.method
        if (method.equals(HttpMethod.OPTIONS)) return true
        log.info("sssss: ${request.getHeader("userId")}")
        return super.preHandle(request, response, handler)
    }
}