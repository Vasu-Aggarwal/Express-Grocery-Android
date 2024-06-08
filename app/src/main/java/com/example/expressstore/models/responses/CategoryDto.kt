package com.example.expressstore.models.responses

data class CategoryDto(
    val categoryId: Int,
    val categoryName: String,
    val isCoupon: Boolean,
    val coupon: CouponDto
)
