package com.example.hirfa.repository

import com.example.hirfa.R
import com.example.hirfa.model.Category

object CategoryRepository {
    private val categories = listOf(
        Category(
            id = "1",
            name = "Plumbing",
            description = "Plumbing services for homes and businesses",
            icon = R.drawable.plumber
        ),
        Category(
            name = "Electrical",
            description = "Electrical repair and installation services",
            icon = R.drawable.electrical
        ),
        Category(
            name = "Cleaning",
            description = "Professional cleaning services",
            icon = R.drawable.cleaning
        ),
    )


    fun getAllCategories(): List<Category> = categories
}
