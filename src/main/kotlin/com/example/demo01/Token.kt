package com.example.demo01

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import java.lang.annotation.Retention

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
annotation class Token()
