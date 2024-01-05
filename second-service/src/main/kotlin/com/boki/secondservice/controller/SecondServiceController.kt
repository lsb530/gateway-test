package com.boki.secondservice.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/second-service")
@RestController
class SecondServiceController {

    @Value("\${server.port}")
    private val port: String? = null

    @GetMapping("/check")
    fun check(): String? {
        return String.format("Second Service on PORT %s", port)
    }

}
