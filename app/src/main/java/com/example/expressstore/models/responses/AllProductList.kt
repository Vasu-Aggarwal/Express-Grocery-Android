package com.example.expressstore.models.responses

import com.example.expressstore.models.requests.CategoryDto
import com.example.expressstore.models.requests.UserRegisterRequest
import java.sql.Timestamp

data class AllProductList(
    val productId: Int,
    val productPrice: Double,
    val inStockQuantity: Int,
    val productName: String,
    val isAvailable: Boolean,
    val aboutProduct: String,
    val productImg: String,
    val addedBy: UserRegisterRequest,
    val addedOn: Timestamp,
    val categories: List<CategoryDto>,
    val discountOnCategory: Int,
    val productDiscountedPrice: Double
)
