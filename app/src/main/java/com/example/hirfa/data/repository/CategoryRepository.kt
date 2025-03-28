package com.example.hirfa.data.repository

import com.example.hirfa.data.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getCategories(): Flow<List<Category>>

}
