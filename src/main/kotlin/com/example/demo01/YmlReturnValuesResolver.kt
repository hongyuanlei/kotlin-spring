package com.example.demo01

import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.MethodParameter
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodReturnValueHandler
import org.springframework.web.method.support.ModelAndViewContainer
import org.yaml.snakeyaml.Yaml

class YmlReturnValuesResolver: HandlerMethodReturnValueHandler{
    override fun supportsReturnType(returnType: MethodParameter): Boolean {
        return returnType.getMethodAnnotation(Yml::class.java) != null
    }

    override fun handleReturnValue(
        returnValue: Any?,
        returnType: MethodParameter,
        mavContainer: ModelAndViewContainer,
        webRequest: NativeWebRequest
    ) {
        val response = webRequest.getNativeResponse(HttpServletResponse::class.java)
        response?.let {
            response.contentType = "text/plain;charset=utf-8"
            response.writer.print(Yaml().dump(returnValue))
        }
        mavContainer.isRequestHandled = true //????
    }
}