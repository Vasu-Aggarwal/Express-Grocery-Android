package com.example.expressstore.repositories

import android.util.Log
import com.example.expressstore.models.requests.AddProductToCartRequest
import com.example.expressstore.models.requests.RemoveFromCartRequest
import com.example.expressstore.models.responses.AddProductToCartResponse
import com.example.expressstore.models.responses.CartCountResponse
import com.example.expressstore.models.responses.ListCartDetailsResponse
import com.example.expressstore.models.responses.RemoveFromCartResponse
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

    private val _addToCart = MutableStateFlow<NetworkResult<AddProductToCartResponse>>(NetworkResult.Idle())
    val addToCart : StateFlow<NetworkResult<AddProductToCartResponse>>
        get() = _addToCart

    private val _removeFromCart = MutableStateFlow<NetworkResult<RemoveFromCartResponse>>(NetworkResult.Idle())
    val removeFromCart : StateFlow<NetworkResult<RemoveFromCartResponse>>
        get() = _removeFromCart

    private val _cartDetails = MutableStateFlow<NetworkResult<ListCartDetailsResponse>>(NetworkResult.Idle())
    val cartDetails : StateFlow<NetworkResult<ListCartDetailsResponse>>
        get() = _cartDetails

    suspend fun getCartCount(){
        val authToken = tokenManager.getAuthToken()
        val response = cartService.getCartCount("Bearer $authToken", tokenManager.getUserUuid().toString())
        if (response.isSuccessful && response.body()!=null){
            _cartCount.emit(NetworkResult.Success(response.body()!!))
        } else {
            _cartCount.emit(NetworkResult.Error(response.errorBody()?.string()!!))
        }
    }

    suspend fun addProductToCart(product: Int, quantity: Int){
        val authToken = tokenManager.getAuthToken()
        val userUuid = tokenManager.getUserUuid()
        val addProductToCartRequest = AddProductToCartRequest(userUuid.toString(), quantity, product)
        val response = cartService.addToCart("Bearer $authToken", addProductToCartRequest)
        if (response.isSuccessful && response.body()!=null){
            _addToCart.emit(NetworkResult.Success(response.body()!!))
        } else {
            _addToCart.emit(NetworkResult.Error(response.errorBody()?.string()!!))
        }
    }

    suspend fun removeFromCart(product: Int){
        val authToken = tokenManager.getAuthToken()
        val userUuid = tokenManager.getUserUuid()
        val removeFromCartRequest = RemoveFromCartRequest(userUuid.toString(), product)
        val response = cartService.removeFromCart("Bearer $authToken", removeFromCartRequest)
        if (response.isSuccessful && response.body()!=null){
            _removeFromCart.emit(NetworkResult.Success(response.body()!!))
        } else {
            _removeFromCart.emit(NetworkResult.Error(response.errorBody()?.string()!!))
        }
    }

    suspend fun getCartDetails(){
        val authToken = tokenManager.getAuthToken()
        val userUuid = tokenManager.getUserUuid()
        val response = cartService.getCartDetails("Bearer $authToken", userUuid.toString(), 1)
        if (response.isSuccessful && response.body()!=null){
            _cartDetails.emit(NetworkResult.Success(response.body()!!))
        } else {
            _cartDetails.emit(NetworkResult.Error(response.errorBody()!!.string()))
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

    fun decrementCartCount() {
        _cartCount.update { currentResult ->
            if (currentResult is NetworkResult.Success) {
                val newCount = currentResult.data!!.cartCount - 1
                val updatedResponse = currentResult.data.copy(cartCount = newCount)
                NetworkResult.Success(updatedResponse)
            } else {
                currentResult
            }
        }
    }
}