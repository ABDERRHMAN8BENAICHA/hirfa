package com.example.hirfa.presentation.viewmodel

import com.example.hirfa.data.model.Category

data class CategoryState(
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null // Changed from `error` to `errorMessage`
)
