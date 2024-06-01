package com.example.expressstore.models.requests

data class CategoryDto(
    val categoryId: Int,
    val categoryName: String,
    val isCoupon: Boolean,
    val coupon: Int
)
