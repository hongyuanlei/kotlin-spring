package com.example.demo01

import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext
import org.springframework.context.ApplicationContext
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerExecutionChain
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

fun main() {
    val applicationContext = AnnotationConfigServletWebServerApplicationContext(WebConfig::class.java)

    runTest(applicationContext, MockHttpServletRequest("PUT", "/test3").apply {
        addHeader("token", "token132342344")
    })

    runTest(applicationContext, MockHttpServletRequest("PUT", "/test4"))
}

fun runTest(applicationContext: ApplicationContext, mockHttpServletRequest: MockHttpServletRequest) {

    val handlerMapping = applicationContext.getBean(RequestMappingHandlerMapping::class.java)
    val handlerMethods: MutableMap<RequestMappingInfo, HandlerMethod> = handlerMapping.handlerMethods
    handlerMethods.forEach { (k, v) ->
        println("$k=$v")
    }
    val chain: HandlerExecutionChain? = handlerMapping.getHandler(mockHttpServletRequest)
    println(chain)

    val mockHttpServletResponse = MockHttpServletResponse()
    val handlerAdapter = applicationContext.getBean(MyRequestMappingHandlerAdapter::class.java)
    chain?.let {
        handlerAdapter.invokeHandlerMethod(
            request = mockHttpServletRequest,
            response = mockHttpServletResponse,
            handlerMethod = chain.handler as HandlerMethod
        )
    }

    println("response content: ${mockHttpServletResponse.contentAsString}")
}