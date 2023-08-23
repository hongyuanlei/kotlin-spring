package com.example.demo01

import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties(WebMvcProperties::class, ServerProperties::class)
class WebConfig {

    @Bean
    fun tomcatServletWebServerFactory(serverProperties: ServerProperties): TomcatServletWebServerFactory {
        return TomcatServletWebServerFactory(serverProperties.port)
    }

    @Bean
    fun dispatcherServlet(): DispatcherServlet {
        return DispatcherServlet()
    }

    @Bean
    fun dispatcherServletRegistrationBean(
        dispatcherServlet: DispatcherServlet,
        webMvcProperties: WebMvcProperties
    ): DispatcherServletRegistrationBean {
        val registrationBean = DispatcherServletRegistrationBean(dispatcherServlet, "/")
        registrationBean.setLoadOnStartup(webMvcProperties.servlet.loadOnStartup)
        return registrationBean
    }

    @Bean
    fun requestMappingHandlerMapping(): RequestMappingHandlerMapping {
        return RequestMappingHandlerMapping()
    }

    @Bean
    fun requestMappingHandlerAdapter(): MyRequestMappingHandlerAdapter {
        val myRequestMappingHandlerAdapter = MyRequestMappingHandlerAdapter()

        val tokenResolver = TokenArgumentResolver()
        val ymlResponseResolver = YmlReturnValuesResolver()
        myRequestMappingHandlerAdapter.customArgumentResolvers = listOf(tokenResolver)
        myRequestMappingHandlerAdapter.customReturnValueHandlers = listOf(ymlResponseResolver)
        return myRequestMappingHandlerAdapter
    }

}