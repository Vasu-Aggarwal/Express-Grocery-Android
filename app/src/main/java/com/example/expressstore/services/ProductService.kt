package com.example.expressstore.services

import com.example.expressstore.models.responses.AllProductList
import com.example.expressstore.models.responses.AllProductListPaginationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ProductService {
    @GET("/api/product/allProductList")
    suspend fun productList(
        @Header("Authorization") token: String,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int,
        @Query("uuid") userUuid: String,
        @Query("sortBy") sortBy: String
    ): Response<AllProductListPaginationResponse>

    @GET("/api/product/searchProduct")
    suspend fun getProductsByCategory(@Header("Authorization") token: String, @Query("categoryName") categoryName: String): Response<List<AllProductList>>
}