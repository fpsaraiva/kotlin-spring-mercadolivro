package com.mercadolivro.controller

import com.mercadolivro.service.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admin")
class AdminController(
    val customerService: CustomerService
) {

    @GetMapping("/report")
    fun getAll(): String {
        return "This is a admin-specific report."
    }
}