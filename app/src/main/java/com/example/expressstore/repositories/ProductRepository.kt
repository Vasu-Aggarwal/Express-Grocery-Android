package com.example.expressstore.repositories

import android.util.Log
import com.example.expressstore.models.responses.AllProductList
import com.example.expressstore.models.responses.UserLoginResponse
import com.example.expressstore.services.ProductService
import com.example.expressstore.services.TokenManager
import com.example.expressstore.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productService: ProductService,
                                            private val tokenManager: TokenManager) {
    private val _products = MutableStateFlow<NetworkResult<List<AllProductList>>>(NetworkResult.Loading())
    val products : StateFlow<NetworkResult<List<AllProductList>>>
        get() = _products

    suspend fun productList(){
        val authToken = tokenManager.getAuthToken()
        val response = productService.productList("Bearer $authToken")
        if (response.isSuccessful && response.body()!=null){
            _products.emit(NetworkResult.Success(response.body()!!))
        } else {
            _products.emit(NetworkResult.Error(response.errorBody()?.string()!!))
        }
    }
}