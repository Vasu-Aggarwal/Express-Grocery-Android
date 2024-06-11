package com.example.expressstore.repositories

import android.util.Log
import com.example.expressstore.models.responses.AllProductList
import com.example.expressstore.models.responses.AllProductListPaginationResponse
import com.example.expressstore.models.responses.UserLoginResponse
import com.example.expressstore.services.ProductService
import com.example.expressstore.services.TokenManager
import com.example.expressstore.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productService: ProductService,
                                            private val tokenManager: TokenManager) {
    private val _products = MutableStateFlow<NetworkResult<AllProductListPaginationResponse>>(NetworkResult.Loading())
    val products : StateFlow<NetworkResult<AllProductListPaginationResponse>>
        get() = _products

    private val _productCategory = MutableStateFlow<NetworkResult<List<AllProductList>>>(NetworkResult.Loading())
    val productCategory : StateFlow<NetworkResult<List<AllProductList>>>
        get() = _productCategory

    suspend fun productList(pageNumber: Int){
        val authToken = tokenManager.getAuthToken()
        val userUuid = tokenManager.getUserUuid()
        val response = productService.productList("Bearer $authToken", pageNumber, 10, userUuid.toString(), "productName")
        if (response.isSuccessful && response.body()!=null){
            _products.emit(NetworkResult.Success(response.body()!!))
        } else {
            _products.emit(NetworkResult.Error(response.errorBody()?.string()!!))
        }
    }

    suspend fun getProductsByCategory(category: String){
        val authToken = tokenManager.getAuthToken()
        val response = productService.getProductsByCategory("Bearer $authToken", category)
        if (response.isSuccessful && response.body()!=null){
            _productCategory.emit(NetworkResult.Success(response.body()!!));
        } else {
            _productCategory.emit(NetworkResult.Error(response.errorBody()!!.string()))
        }
    }
}