package com.example.expressstore.models.requests

data class AddProductToCartRequest(
    val userUuid: String,
    val productQuantity: Int,
    val product: Int
)
