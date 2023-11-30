package com.boki.secondservice.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/")
@RestController
class HealthController {

    @Value("\${server.port}")
    private val port: String? = null

    @GetMapping
    fun health(): String? {
        return String.format("health Check with React Service on PORT %s", port)
    }

}