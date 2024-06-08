package com.example.expressstore.models.responses

import java.time.LocalDateTime

data class AddProductToCartResponse(
    val cartDetailId: Int,
    val productQuantity: Int,
    val cart: CartResponse,
    val createdOn: LocalDateTime,
    val product: AllProductList,
    val productAmount: Double,
    val productDiscountedAmount: Double
)