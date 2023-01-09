package com.example.flutterBlogApi.config

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
// API의 일부분으로 문서화하기 위해 사용.
@MustBeDocumented
annotation class AuthToken()

