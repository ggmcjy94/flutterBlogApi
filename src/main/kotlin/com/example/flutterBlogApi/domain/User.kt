package com.example.flutterBlogApi.domain

import javax.persistence.*

@Entity
@Table(name = "users")
class User  (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,

    @Column(unique = true)
    val email: String,
    @Column
    val password: String,
    @Column
    val username : String,
    @Column
    val tel : String,

) : BaseEntity() {

}