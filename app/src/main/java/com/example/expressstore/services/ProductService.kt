package com.example.expressstore.services

import com.example.expressstore.models.responses.AllProductList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ProductService {
    @GET("/api/product/allProductList")
    suspend fun productList(@Header("Authorization") token: String): Response<List<AllProductList>>

    @GET("/api/product/searchProduct")
    suspend fun getProductsByCategory(@Header("Authorization") token: String, @Query("categoryName") categoryName: String): Response<List<AllProductList>>
}