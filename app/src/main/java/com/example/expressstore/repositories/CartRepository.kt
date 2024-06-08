package com.example.expressstore.repositories

import android.util.Log
import com.example.expressstore.models.responses.CartCountResponse
import com.example.expressstore.services.CartService
import com.example.expressstore.services.TokenManager
import com.example.expressstore.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartService: CartService,
                                        private val tokenManager: TokenManager) {
    private val _cartCount = MutableStateFlow<NetworkResult<CartCountResponse>>(NetworkResult.Idle())
    val cartCount : StateFlow<NetworkResult<CartCountResponse>>
        get() = _cartCount

    suspend fun getCartCount(){
        val authToken = tokenManager.getAuthToken()
        Log.i("USER UUID:", tokenManager.getUserUuid().toString())
        val response = cartService.getCartCount("Bearer $authToken", tokenManager.getUserUuid().toString())
        if (response.isSuccessful && response.body()!=null){
            _cartCount.emit(NetworkResult.Success(response.body()!!))
            Log.i("Cart Count", "cartRepo: "+response.body())
        } else {
            _cartCount.emit(NetworkResult.Error(response.errorBody()?.string()!!))
            Log.i("Cart Count", response.errorBody()?.string()!!)
        }
    }

    fun incrementCartCount() {
        _cartCount.update { currentResult ->
            if (currentResult is NetworkResult.Success) {
                val newCount = currentResult.data!!.cartCount + 1
                val updatedResponse = currentResult.data.copy(cartCount = newCount)
                NetworkResult.Success(updatedResponse)
            } else {
                currentResult
            }
        }
    }
}