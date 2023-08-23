package com.example.demo01

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.yaml.snakeyaml.Yaml

@Controller
class Controller1 {
    companion object {
        private val logger = LoggerFactory.getLogger(Controller1::class.java)
    }

    @GetMapping("/test1")
    fun test1(): ModelAndView? {
        logger.info("test1()")
        return null
    }

    @PostMapping("/test2")
    fun test2(@RequestParam("name") name: String): ModelAndView? {
        logger.info("test2($name)")
        return null
    }

    @PutMapping("/test3")
    fun test3(@Token token: String): ModelAndView? {
        logger.info("test3($token)")
        return null
    }

    @RequestMapping("/test4")
    @Yml
    fun test4(): User {
        logger.info("test4")
        return User("ylhong")
    }
}
fun main() {
    println(Yaml().dump(User("ylhong")))
}

class User(val name: String)