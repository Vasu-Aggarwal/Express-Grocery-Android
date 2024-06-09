package com.example.expressstore.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expressstore.models.responses.AddProductToCartResponse
import com.example.expressstore.models.responses.CategoryDto
import com.example.expressstore.models.responses.CouponDto
import com.example.expressstore.models.responses.UserRegisterResponse
import com.example.expressstore.models.responses.AllProductList
import com.example.expressstore.utils.NetworkResult
import com.example.expressstore.viewmodels.AllProductListViewModel
import com.example.expressstore.viewmodels.CartViewModel
import java.sql.Timestamp

@Composable
fun HomeScreen(viewModel: AllProductListViewModel = hiltViewModel(), cartViewModel: CartViewModel = hiltViewModel()){
    val products: State<NetworkResult<List<AllProductList>>> = viewModel.products.collectAsState()
    LazyColumn {
        products.value.data?.let { productList ->
            items(productList) { product ->
                ProductItem(product = product, cartViewModel, viewModel)
            }
        }
    }
}

@Composable
fun ProductItem(
    product: AllProductList,
    cartViewModel: CartViewModel,
    viewModel: AllProductListViewModel
){
    val context = LocalContext.current
    Box(modifier = Modifier
        .padding(4.dp)
        .background(Color.White)
//        .height(300.dp)
    ){
        Row {
            Text(text = "Product Image")
            Column {
                Text(text = product.productName)
                Row {
                    Text(text = "₹"+product.productDiscountedPrice.toString())
                    Text(text = "M.R.P:"+product.productPrice.toString(), textDecoration = TextDecoration.LineThrough)
                }
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .align(Alignment.CenterHorizontally)
                    , onClick = {
                        cartViewModel.increaseLocalCartCount()
                        //call the api to add the product to the cart
                        viewModel.addProductToCart(product.productId, 1)
                    }) {
                    Text(text = "Add to cart")
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductItemPreview(){
    Log.i("preview started", "ProductItemPreview: ")
    val userRequest = UserRegisterResponse("", "", "", "", "", false)
    val couponDto = CouponDto(1, 100.00, 10, "CUSTOMER", "2024-06-01 12:30:45", "Test", 100.00, 1, "2024-06-01 12:30:45")
    val categoryDto = CategoryDto(1, "Electronics", false, couponDto)
    val timestampString = "2024-06-01 12:30:45"
    val timestamp = Timestamp.valueOf(timestampString)
    val productList = AllProductList(
        1,
        2000.00,
        1,
        "Test Product",
        true,
        "This is a test product",
        "",
        userRequest,
        timestamp,
        listOf(categoryDto),
        1000,
        1000.00
    )
//    ProductItem(product = productList, cartViewModel = CartViewModel())
}