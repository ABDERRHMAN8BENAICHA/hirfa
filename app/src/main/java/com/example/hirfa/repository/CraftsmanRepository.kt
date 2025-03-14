package com.example.hirfa.repository

import com.example.hirfa.R
import com.example.hirfa.model.Craftsman

object CraftsmanRepository {
    private val craftsmen = listOf(
        Craftsman(
            id = "1",
            name = "John Doe",
            description = "Professional plumbing services for homes and businesses.",
            category = "Plumbing",
            profilePicture = R.drawable.john_doe,
            phoneNumber = "+1234567890",
            rating = 4.5f,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        ),
        Craftsman(
            id = "2",
            name = "Jane Smith",
            description = "Safe and reliable electrical repair services.",
            category = "Electrical",
            profilePicture = R.drawable.jane_smith,
            phoneNumber = "+0987654321",
            rating = 4.7f,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
    )

    fun getAllCraftsman(): List<Craftsman> = craftsmen
}