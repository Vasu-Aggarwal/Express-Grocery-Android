package com.example.expressstore.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expressstore.models.responses.CartCountResponse
import com.example.expressstore.navigation.BottomNavigationItem
import com.example.expressstore.utils.NetworkResult
import com.example.expressstore.viewmodels.CartViewModel

@Composable
fun BottomNavigationBar(){

    val cartViewModel: CartViewModel = hiltViewModel()
    var cartCount by remember { mutableIntStateOf(1) }
    val cartCountValue: State<NetworkResult<CartCountResponse>> = cartViewModel.cartCount.collectAsState()
    LaunchedEffect(Unit) {
        cartViewModel.getCartCount()
    }

    when(val result = cartCountValue.value){
        is NetworkResult.Error -> {}
        is NetworkResult.Idle -> {}
        is NetworkResult.Loading -> {}
        is NetworkResult.Success -> {cartCount = result.data!!.cartCount
        }
    }

    val bottomItems = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedItem = Icons.Outlined.Home,
            hasNews = false
        ),

        BottomNavigationItem(
            title = "Categories",
            selectedIcon = Icons.Filled.Menu,
            unselectedItem = Icons.Outlined.Menu,
            hasNews = false
        ),

        BottomNavigationItem(
            title = "Cart",
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedItem = Icons.Outlined.ShoppingCart,
            hasNews = false,
            badgeCount = cartCount
        ),

        BottomNavigationItem(
            title = "Account",
            selectedIcon = Icons.Filled.Person,
            unselectedItem = Icons.Outlined.Person,
            hasNews = false
        )
    )

    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(bottomBar = {
            NavigationBar {
                bottomItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(
                                when (index) {
                                    0 -> "home"
                                    1 -> "category"
                                    2 -> "cart"
                                    3 -> "account"
                                    else -> "home"
                                }
                            )
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    } else if (item.hasNews) {
                                        Badge()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (index == selectedItemIndex)
                                        item.selectedIcon
                                    else
                                        item.unselectedItem, contentDescription = item.title
                                )
                            }
                        }
                    )
                }
            }
    }){
            innerPadding ->
        NavHost(navController = navController, startDestination = "home", modifier = Modifier.padding(innerPadding)){

            composable(route = "home"){
                HomeScreen(cartViewModel = cartViewModel)
            }

            composable(route = "cart"){
                CartScreen()
            }

            composable(route = "account"){
                AccountScreen()
            }

            composable(route = "category"){
                CategoryScreen()
            }
        }
    }
}