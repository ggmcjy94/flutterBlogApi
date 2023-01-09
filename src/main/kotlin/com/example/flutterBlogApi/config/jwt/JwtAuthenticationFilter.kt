package com.example.flutterBlogApi.config.jwt


import antlr.StringUtils
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtAuthenticationFilter (
    private val jwtTokenProvider: JwtTokenProvider
) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain) {
        //1 . request header 에서 jwt token 추출
        val token : String? = resolveToken(request as HttpServletRequest)
        println("token : $token")
        if (token != null && jwtTokenProvider.validateToken(token)) {
            val authentication = jwtTokenProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(request, response)
    }


    fun resolveToken(request: HttpServletRequest): String? {
        val header = request.getHeader("Authorization")
        if (header != null) {
            val token = header.split(" ")[1]
            return token
        }
        return ""

    }


}