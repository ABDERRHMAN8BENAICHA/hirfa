package com.example.hirfa.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hirfa.domain.usecase.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _categoryState = MutableStateFlow(CategoryState())
    val categoryState: StateFlow<CategoryState> = _categoryState.asStateFlow()

    init {
        loadCategories()
    }

    fun loadCategories() {
        viewModelScope.launch {
            getCategoriesUseCase()
                .onStart {
                    _categoryState.update { it.copy(isLoading = true, errorMessage = null) }
                }
                .catch { e ->
                    _categoryState.update {
                        it.copy(isLoading = false, errorMessage = "Failed to load categories: ${e.localizedMessage}")
                    }
                }
                .collect { categories ->
                    _categoryState.update {
                        it.copy(categories = categories, isLoading = false)
                    }
                }
        }
    }
}

