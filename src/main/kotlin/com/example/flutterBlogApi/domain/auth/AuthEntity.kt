package com.example.flutterBlogApi.domain.auth

import javax.persistence.*


@Entity
@Table(name = "auth")
class AuthEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,

    @Column(nullable = false)
    var refreshToken : String,

    @Column(nullable = false)
    val keyEmail : String,
//    val userAgent : String, 기기 마다 refresh token 같이 저장
) {

    fun updateRefreshToken(createRefreshToken: String) {
        this.refreshToken = createRefreshToken
    }
}