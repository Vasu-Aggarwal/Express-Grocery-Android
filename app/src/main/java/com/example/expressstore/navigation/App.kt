package com.example.expressstore.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expressstore.models.responses.CartCountResponse
import com.example.expressstore.models.responses.UserLoginResponse
import com.example.expressstore.screens.AccountScreen
import com.example.expressstore.screens.BottomNavigationBar
import com.example.expressstore.screens.CartScreen
import com.example.expressstore.screens.CategoryScreen
import com.example.expressstore.screens.HomeScreen
import com.example.expressstore.screens.LoginScreen
import com.example.expressstore.utils.NetworkResult
import com.example.expressstore.viewmodels.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(){
    val navController = rememberNavController()
//    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
//
//    val cartViewModel: CartViewModel = hiltViewModel()
//    var cartCount by remember { mutableIntStateOf(0) }
//    val cartCountValue: State<NetworkResult<CartCountResponse>> = cartViewModel.cartCount.collectAsState()
//
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//    var isLoggedIn by rememberSaveable { mutableStateOf(false) }
//
//    LaunchedEffect(currentRoute) {
//        if (currentRoute != "login" && !isLoggedIn) {
//            isLoggedIn = true
//            cartViewModel.getCartCount()
//        }
//    }

//    when(val result = cartCountValue.value){
//        is NetworkResult.Error -> {}
//        is NetworkResult.Idle -> {}
//        is NetworkResult.Loading -> {}
//        is NetworkResult.Success -> {cartCount = result.data!!.cartCount
//        }
//    }



//    Scaffold(bottomBar = {
//        if (currentRoute != "login") {
//            NavigationBar {
//                bottomItems.forEachIndexed { index, item ->
//                    NavigationBarItem(
//                        selected = selectedItemIndex == index,
//                        onClick = {
//                            selectedItemIndex = index
//                            navController.navigate(
//                                when (index) {
//                                    0 -> "home"
//                                    1 -> "category"
//                                    2 -> "cart"
//                                    3 -> "account"
//                                    else -> "home"
//                                }
//                            )
//                        },
//                        label = {
//                            Text(text = item.title)
//                        },
//                        icon = {
//                            BadgedBox(
//                                badge = {
//                                    if (item.badgeCount != null) {
//                                        Badge {
//                                            Text(text = item.badgeCount.toString())
//                                        }
//                                    } else if (item.hasNews) {
//                                        Badge()
//                                    }
//                                }
//                            ) {
//                                Icon(
//                                    imageVector = if (index == selectedItemIndex)
//                                        item.selectedIcon
//                                    else
//                                        item.unselectedItem, contentDescription = item.title
//                                )
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    }){
//        innerPadding ->
        NavHost(navController = navController, startDestination = "login"){
            composable(route = "login"){
                LoginScreen(navController = navController)
            }

            composable(route = "bottomBar"){
                BottomNavigationBar()
            }
//            composable(route = "home"){
//                HomeScreen()
//            }
//
//            composable(route = "cart"){
//                CartScreen()
//            }
//
//            composable(route = "account"){
//                AccountScreen()
//            }
//
//            composable(route = "category"){
//                CategoryScreen()
//            }
        }
//    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedItem: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)
