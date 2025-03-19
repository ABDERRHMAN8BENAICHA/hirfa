package com.example.hirfa.model

data class Craftsman(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val profilePicture: Int,
    val phoneNumber: String,
    val rating: Float,
    val createdAt: Long,
    val updatedAt: Long
)
