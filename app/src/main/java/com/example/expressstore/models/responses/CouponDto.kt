package com.example.expressstore.models.responses

data class CouponDto(
    val couponId: Int,
    val maxDiscount: Double,
    val discountPercent: Int,
    val couponType: String,
    val couponExpireDate: String,
    val couponName: String,
    val minimumCartValue: Double,
    val couponStatus: Int,
    val createdOn: String
)
