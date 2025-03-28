package com.example.hirfa.data.repository

import com.example.hirfa.R
import com.example.hirfa.data.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class CategoryRepositoryImpl : CategoryRepository {
    private val _categories = mutableListOf(
        Category(
            id = "1",
            name = "Plumbing",
            description = "Plumbing services for homes and businesses",
            icon = R.drawable.plumber
        ),
        Category(
            id = "2",
            name = "Electrical",
            description = "Electrical repair and installation services",
            icon = R.drawable.electrical
        ),
        Category(
            id = "3",
            name = "Cleaning",
            description = "Professional cleaning services",
            icon = R.drawable.cleaning
        ),
        Category(
            id = "4",
            name = "Carpenter",
            description = "Professional cleaning services",
            icon = R.drawable.carpenter
        ),
        Category(
            id = "5",
            name = "Chef",
            description = "Professional cleaning services",
            icon = R.drawable.chef
        )
    )

    private val _categoryFlow = MutableSharedFlow<List<Category>>(replay = 1)

    init {
        _categoryFlow.tryEmit(_categories.toList())
    }


    override fun getCategories(): Flow<List<Category>> = _categoryFlow
}
