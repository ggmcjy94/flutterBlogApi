package com.example.flutterBlogApi

import com.example.flutterBlogApi.domain.Blog
import com.example.flutterBlogApi.model.BlogRequest
import com.example.flutterBlogApi.repository.BlogRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import javax.annotation.PostConstruct

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan
class FlutterBlogApiApplication

fun main(args: Array<String>) {
	runApplication<FlutterBlogApiApplication>(*args)
}


