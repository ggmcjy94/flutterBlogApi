package com.example.flutterBlogApi.exception

import io.jsonwebtoken.ExpiredJwtException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)


    @ExceptionHandler
    fun handleServerException(e : ServerException) : ErrorResponse {
        log.error( e.message )
        return ErrorResponse(code = e.code, message = e.message)
    }

    @ExceptionHandler(ExpiredJwtException::class)
    fun handleTokenExpiredException(e:ExpiredJwtException) : ErrorResponse {
        log.error (e.message)
        return ErrorResponse(code = 401, message = "Token Expired Error")
    }


    @ExceptionHandler(Exception::class)
    fun handleException(e:Exception) : ErrorResponse {
        log.error ( e.message )
        return ErrorResponse(code = 500, message = "Internal Server Error") // 에러 메시지는 직접 입력 해커로부터 보안 (언어 , db 필드 등등)
    }


    @ExceptionHandler(UserNotFoundException::class)
    fun handleException(e:UserNotFoundException) : ResponseEntity<ErrorResponse> {
        log.error ( e.message )
        val errorResponse = ErrorResponse(code = e.code, message = e.message)
        return ResponseEntity.status(errorResponse.code).body(errorResponse);
    }


}