package com.example.expressstore.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.expressstore.models.responses.AddProductToCartResponse
import com.example.expressstore.models.responses.ListCartDetailsResponse
import com.example.expressstore.models.responses.RemoveFromCartResponse
import com.example.expressstore.utils.NetworkResult
import com.example.expressstore.viewmodels.AllProductListViewModel
import com.example.expressstore.viewmodels.CartViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun CartScreen(cartViewModel: CartViewModel = hiltViewModel(), navController: NavHostController, viewModel: AllProductListViewModel = hiltViewModel()){
    val cartDetails: State<NetworkResult<ListCartDetailsResponse>> = cartViewModel.cartDetails.collectAsState()
    when(val result = cartDetails.value){
        is NetworkResult.Error -> {}
        is NetworkResult.Idle -> {}
        is NetworkResult.Loading -> CircularProgressIndicator()
        is NetworkResult.Success -> {
            if (result.data?.totalProducts!!.toInt() == 0) {
                Button(onClick = {
                    navController.navigate("home") {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }) {
                    Text(text = "Find Products")
                }
            } else {
                Column {
                    SubTotal(result.data)
                    LazyColumn (userScrollEnabled = true, modifier = Modifier.fillMaxSize()){
                        result.data.productDetail.let { products ->
                            items(products){ product ->
                                ProductDetailItem(product = product, cartViewModel, viewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SubTotal(data: ListCartDetailsResponse?) {
    Column {
        Text(text = "Subtotal Rs."+data?.afterDiscount.toString())
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Proceed to Buy ("+data?.totalProducts+" items)")
        }
        HorizontalDivider()
    }
}

@Composable
fun ProductDetailItem(
    product: AddProductToCartResponse,
    cartViewModel: CartViewModel,
    viewModel: AllProductListViewModel
) {
    var quantityInCart by rememberSaveable { mutableIntStateOf(product.productQuantity) }
    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()){
        if (isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
        Column{
            Row {
                Text(text = product.product.productImg)
                Column {
                    Text(text = product.product.productName)
                    Text(text = product.product.productDiscountedPrice.toString())
                    if (product.product.isAvailable) {
                        Text(text = "In Stock")
                    } else {
                        Text(text = "Out of Stock")
                    }
                }
            }
            Row {
                //show three buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
//                        .align(Alignment.CenterHorizontally),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        isLoading.value = true
                        cartViewModel.decreaseLocalCartCount()
//                        viewModel.removeProductFromCart(product.product.productId)
                        cartViewModel.removeProductAndRefreshCart(product.product.productId)
                        isLoading.value = false
                        quantityInCart--
                    }) {
                        if (quantityInCart == 1) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete the product"
                            )
                        }    else {
                            Text(text = "-")
                        }
                    }
                    Text(
                        text = quantityInCart.toString(),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Button(onClick = {
                        quantityInCart++
                        cartViewModel.increaseLocalCartCount()
                        viewModel.addProductToCart(product.product.productId, 1)
                    }) {
                        Text(text = "+")
                    }
                }
            }
        }
            }
    }
}