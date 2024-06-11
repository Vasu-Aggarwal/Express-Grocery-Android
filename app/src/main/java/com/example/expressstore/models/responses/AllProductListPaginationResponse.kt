package com.example.expressstore.models.responses

data class AllProductListPaginationResponse(
    val products: List<AllProductList>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalProducts: Int,
    val totalPages: Int,
    val isLastPage: Boolean
)
