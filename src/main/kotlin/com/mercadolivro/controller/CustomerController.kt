package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.model.CustomerModel
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
class CustomerController {

    @GetMapping
    fun getCustomer(): CustomerModel {
        return CustomerModel("1", "Fernando", "email@email.com")
    }

    @PostMapping
    fun createCustomer(@RequestBody customer: PostCustomerRequest): PostCustomerRequest {
        return customer
    }
}