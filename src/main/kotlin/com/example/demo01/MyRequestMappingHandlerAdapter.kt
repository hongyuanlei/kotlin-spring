package com.example.demo01

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter

open class MyRequestMappingHandlerAdapter: RequestMappingHandlerAdapter() {
    public override fun invokeHandlerMethod(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handlerMethod: HandlerMethod
    ): ModelAndView? {
        return super.invokeHandlerMethod(request, response, handlerMethod)
    }
}