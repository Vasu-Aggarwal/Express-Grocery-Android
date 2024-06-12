package com.example.expressstore.models.responses

data class ListCartDetailsResponse(
    val productDetail: List<AddProductToCartResponse>,
    val totalInCartItems: Int,
    val totalProducts: Long,
    val beforeDiscount: Double,
    val afterDiscount: Double,
    val savedAmount: Double
)
