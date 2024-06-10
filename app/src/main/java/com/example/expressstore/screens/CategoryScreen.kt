package com.example.expressstore.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expressstore.models.responses.CategoryDto
import com.example.expressstore.models.responses.CouponDto
import com.example.expressstore.utils.NetworkResult
import com.example.expressstore.viewmodels.CategoryViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun CategoryScreen(categoryViewModel: CategoryViewModel = hiltViewModel()){
    val categories: State<NetworkResult<List<CategoryDto>>> = categoryViewModel.category.collectAsState()
    LazyVerticalGrid (
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(5.dp)
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

    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier.size(250.dp, 180.dp).padding(5.dp),

    ) {
        Box(
            modifier = Modifier.padding(5.dp, 5.dp).fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(fontSize = 14.sp, text = category.categoryName)
        }
    }

}

@Composable
@Preview
fun CategoryScreenPreview(){
    val couponDto = CouponDto(1, 100.00, 10, "CATEGORY", "", "", 100.00, 1, "")
    val categoryDto = CategoryDto(1, "Test", false, couponDto)
    CategoryItem(category = categoryDto)
}