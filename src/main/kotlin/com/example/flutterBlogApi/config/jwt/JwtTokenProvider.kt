package com.example.flutterBlogApi.config.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

@Component

class JwtTokenProvider (
    private val jwtProperties: JWTProperties,
    private val userDetailsService : UserDetailsService
) {


    private var secretKey = jwtProperties.secret

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }


    val log = LoggerFactory.getLogger(javaClass)


    fun createAccessToken() :String {
        val claims = Jwts.claims().setSubject(jwtProperties.subject)
        val now = Date();
        val token = Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + jwtProperties.expiresTime * 1000) )
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact()
        return token
    }

    fun validateToken(token : String) : Boolean {
        return try {
            !parseJwtToken(token).expiration.before(Date())
        } catch (e : SecurityException) {
            log.error("Invalid JWT Token $e")
            false
        } catch (e : ExpiredJwtException) {
            log.info("Expired JWT Token $e")
            false
        } catch (e : UnsupportedJwtException) {
            log.info("Unsupported JWT Token $e")
            false
        } catch (e : IllegalArgumentException) {
            log.info("JWT claims string is empty. $e")
            false
        }
    }

    // JWT 토큰에서 인증 정보 조회
    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getUserPk(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    // 토큰에서 회원 정보 추출
    fun getUserPk(token: String): String {
        return parseJwtToken(token).subject
    }

    fun parseJwtToken(token:String) : Claims {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token).body
    }

}