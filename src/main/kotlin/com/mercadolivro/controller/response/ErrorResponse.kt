package com.mercadolivro.controller.response

data class ErrorResponse(
    var statusCode: Int,
    var message: String,
    var internalCode: String,
    var errors: List<FieldErrorResponse>?
)
