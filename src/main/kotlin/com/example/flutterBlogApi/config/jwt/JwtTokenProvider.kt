package com.example.flutterBlogApi.config.jwt

import com.example.flutterBlogApi.config.CustomUserDetailService
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

@Component

class JwtTokenProvider (
    private val jwtProperties: JWTProperties,
    private val customUserDetailService: CustomUserDetailService
) {


    private var secretKey = jwtProperties.secret

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }


    val log = LoggerFactory.getLogger(javaClass)


    fun createAccessToken(email: String): String {
        val claims = Jwts.claims().setSubject(email)
        val now = Date()
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + jwtProperties.expiresTime * 1000))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact()
    }

    fun createRefreshToken(email: String): String {
        val claims = Jwts.claims().setSubject(email)
        val now = Date()
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + jwtProperties.reExpiresTime * 1000))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact()
    }

    fun isValidRefreshToken (token : String) : Boolean {
        return try {
            val accessClaims = parseJwtToken(token)
            System.out.println("Access expireTime: " + accessClaims.getExpiration())
            System.out.println("Access userId: " + accessClaims.get("userId"));
            true
        } catch (exception : ExpiredJwtException) {
            System.out.println("Token Expired UserID : " + exception.getClaims().getSubject())
            false
        } catch (exception : JwtException) {
            System.out.println("Token Tampered")
            false
        } catch (exception : NullPointerException) {
            System.out.println("Token is null")
            false
        }
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
        val userDetails = customUserDetailService.loadUserByUsername(getUserPk(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    // 토큰에서 회원 정보 추출
    fun getUserPk(token: String): String? {
        val claims = parseJwtToken(token)
        println("claims:  $claims")
        return claims["sub"] as String
    }

    fun parseJwtToken(token:String) : Claims {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token).body
    }

}