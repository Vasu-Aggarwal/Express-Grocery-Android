package com.example.expressstore.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expressstore.models.responses.AddProductToCartResponse
import com.example.expressstore.models.responses.ListCartDetailsResponse
import com.example.expressstore.utils.NetworkResult
import com.example.expressstore.viewmodels.CartViewModel

@Composable
fun CartScreen(cartViewModel: CartViewModel = hiltViewModel()){

    val cartDetails: State<NetworkResult<ListCartDetailsResponse>> = cartViewModel.cartDetails.collectAsState()
    when(val result = cartDetails.value){
        is NetworkResult.Error -> {}
        is NetworkResult.Idle -> {}
        is NetworkResult.Loading -> CircularProgressIndicator()
        is NetworkResult.Success -> {
            LazyColumn {
                cartDetails.value.data?.productDetail?.let { products ->
                    items(products){ product ->
                        ProductDetailItem(product = product)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductDetailItem(product: AddProductToCartResponse) {
    Box(modifier = Modifier){
        Text(text = product.product.productName)
    }
}