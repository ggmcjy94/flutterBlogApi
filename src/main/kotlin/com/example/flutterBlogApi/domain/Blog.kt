package com.example.flutterBlogApi.domain

import javax.persistence.*

@Entity
@Table
class Blog(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    @Column
    val title: String,
    @Column
    val content : String


):BaseEntity()