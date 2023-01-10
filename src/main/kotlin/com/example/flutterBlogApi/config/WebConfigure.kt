package com.example.flutterBlogApi.config

import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class WebConfigure() : WebMvcConfigurer {

    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        super.configureViewResolvers(registry)
    }
}


@Component
class AuthTokenResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(AuthToken::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        TODO("Not yet implemented")
    }


}
