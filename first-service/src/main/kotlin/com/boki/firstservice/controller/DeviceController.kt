package com.boki.firstservice.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/rest/v1/api/devices")
@RestController
class DeviceController {

    @Value("\${server.port}")
    private val port: String? = null

    @GetMapping
    fun devices(@RequestHeader headers: Map<String, String>): Any? {
        println("headers")
        for ((k, v) in headers) {
            println("key: $k, value: $v")
        }
        println("port: $port")
        return String.format("get devices")
    }

    @GetMapping("/{id}")
    fun device(
        @RequestHeader headers: Map<String, String>,
        @PathVariable id: Any,
    ): Any? {
        println("headers")
        for ((k, v) in headers) {
            println("key: $k, value: $v")
        }
        println("port: $port")
        println("id: $id")
        return String.format("get devices/:%s", id.toString())
    }
}