package com.example.hirfa.repository

import com.example.hirfa.R
import com.example.hirfa.model.Category

object CategoryRepository {
    private val categories = listOf(
        Category(
            id = "1",
            name = "Carpenter",
            description = "Professional cleaning services",
            icon = R.drawable.carpenter
        ),
        Category(
            id = "2",
            name = "Chef",
            description = "Professional cleaning services",
            icon = R.drawable.chef
        ),
        Category(
            id = "3",
            name = "Plumbing",
            description = "Plumbing services for homes and businesses",
            icon = R.drawable.plumber
        ),
        Category(
            id = "4",
            name = "Electrical",
            description = "Electrical repair and installation services",
            icon = R.drawable.electrical
        ),
        Category(
            id = "5",
            name = "Cleaning",
            description = "Professional cleaning services",
            icon = R.drawable.cleaning
        ),
    )


    fun getAllCategories(): List<Category> = categories
}
