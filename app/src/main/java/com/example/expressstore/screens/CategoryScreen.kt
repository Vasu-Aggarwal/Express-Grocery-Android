package com.example.expressstore.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expressstore.models.responses.CategoryDto
import com.example.expressstore.utils.NetworkResult
import com.example.expressstore.viewmodels.CategoryViewModel

@Composable
fun CategoryScreen(categoryViewModel: CategoryViewModel = hiltViewModel()){
    val categories: State<NetworkResult<List<CategoryDto>>> = categoryViewModel.category.collectAsState()
    LazyVerticalGrid (
        columns = GridCells.Fixed(3)
    ){
        categories.value.data?.let { categories ->
            items(categories){ category ->
                CategoryItem(category = category)

            }
        } ?: run {
            items(1){
                Text(text = "Something went wrong")
            }
        }
    }
}

@Composable
fun CategoryItem(category: CategoryDto) {
    Box(modifier = Modifier){
        Text(text = category.categoryName)
    }
}