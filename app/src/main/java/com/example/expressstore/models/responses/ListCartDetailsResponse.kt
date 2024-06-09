package com.example.expressstore.models.responses

data class ListCartDetailsResponse(
    val productDetail: List<AddProductToCartResponse>,
    val totalProducts: Long,
    val beforeDiscount: Double,
    val afterDiscount: Double,
    val savedAmount: Double
)
