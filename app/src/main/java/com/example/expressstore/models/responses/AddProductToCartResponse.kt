package com.example.expressstore.models.responses

data class AddProductToCartResponse(
    val cartDetailId: Int,
    val productQuantity: Int,
    val cart: CartResponse,
    val createdOn: String,
    val product: AllProductList,
    val productAmount: Double,
    val productDiscountedAmount: Double
)