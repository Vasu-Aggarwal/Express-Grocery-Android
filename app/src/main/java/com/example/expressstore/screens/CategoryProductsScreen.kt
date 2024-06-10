package com.example.expressstore.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expressstore.models.responses.AllProductList
import com.example.expressstore.utils.NetworkResult
import com.example.expressstore.viewmodels.AllProductListViewModel
import com.example.expressstore.viewmodels.CartViewModel

@Composable
fun CategoryProductsScreen(categoryName: String, productViewModel: AllProductListViewModel = hiltViewModel(), cartViewModel: CartViewModel = hiltViewModel()){
    LaunchedEffect(categoryName) {
        productViewModel.getProductsByCategoryName(categoryName)
    }
    val products: State<NetworkResult<List<AllProductList>>> = productViewModel.products.collectAsState()
    LazyColumn {
        products.value.data?.let { productList ->
            items(productList) { product ->
                ProductItem(product = product, cartViewModel, productViewModel)
            }
        }
    }
}