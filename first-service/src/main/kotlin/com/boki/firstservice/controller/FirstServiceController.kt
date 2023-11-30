package com.boki.firstservice.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/first-service")
@RestController
class FirstServiceController {

    @Value("\${server.port}")
    private val port: String? = null

    @GetMapping("/check")
    fun check(): String? {
        return String.format("First Service on PORT %s", port)
    }

}