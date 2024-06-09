package com.example.expressstore.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expressstore.models.responses.CategoryDto
import com.example.expressstore.repositories.CategoryRepository
import com.example.expressstore.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository): ViewModel(){

    val category : StateFlow<NetworkResult<List<CategoryDto>>>
        get() = categoryRepository.category

    init {
        getCategories()
    }
    fun getCategories(){
        viewModelScope.launch {
            categoryRepository.getCategories()
        }
    }
}