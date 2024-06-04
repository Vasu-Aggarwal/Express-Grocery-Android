package com.example.expressstore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expressstore.navigation.App
import com.example.expressstore.screens.LoginScreen
import com.example.expressstore.ui.theme.ExpressStoreTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpressStoreTheme {
                val navController = rememberNavController()
                val bottomItems = listOf(
                    BottomNavigationItem(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedItem = Icons.Outlined.Home,
                        hasNews = false
                    ),

                    BottomNavigationItem(
                        title = "Categories",
                        selectedIcon = Icons.Filled.AddCircle,
                        unselectedItem = Icons.Outlined.Home,
                        hasNews = false
                    ),

                    BottomNavigationItem(
                        title = "Cart",
                        selectedIcon = Icons.Filled.ShoppingCart,
                        unselectedItem = Icons.Outlined.ShoppingCart,
                        hasNews = false,
                        badgeCount = 0
                    ),

                    BottomNavigationItem(
                        title = "Account",
                        selectedIcon = Icons.Filled.Person,
                        unselectedItem = Icons.Outlined.Person,
                        hasNews = false
                    )
                )

                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }

                Scaffold(bottomBar = {
                    NavigationBar {
                        bottomItems.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedItemIndex == index,
                                onClick = {
                                      selectedItemIndex = index
//                                    navController.navigate("")
                                },
                                label = {
                                    Text(text = item.title)
                                },
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if(item.badgeCount!=null){
                                                Badge{
                                                    Text(text = item.badgeCount.toString())
                                                }
                                            } else if(item.hasNews){
                                                Badge()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if(index == selectedItemIndex)
                                                             item.selectedIcon
                                                        else
                                                            item.unselectedItem
                                            , contentDescription = item.title)
                                    }
                                })
                        }
                    }
                }){
                }
                App()
            }
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedItem: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)