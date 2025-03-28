package com.example.hirfa.data.repository

import com.example.hirfa.data.model.Craftsman
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow


class CraftsmanRepositoryImpl : CraftsmanRepository{
    private val _craftsmen = mutableListOf(
        Craftsman(
            id = "1",
            name = "John Doe",
            description = "Professional plumbing services for homes and businesses.",
            category = "Plumbing",
            profilePicture = "https://images.unsplash.com/photo-1600596542815-ffad4c1539a9",
            urlImage = "https://images.unsplash.com/photo-1600596542815-ffad4c1539a9",
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
            profilePicture = "https://images.unsplash.com/photo-1590959651373-a3db0f38a961",
            urlImage = "https://images.unsplash.com/photo-1590959651373-a3db0f38a961",
            phoneNumber = "+0987654321",
            rating = 4.7f,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
    )

    private val _craftsmanFlow = MutableSharedFlow<List<Craftsman>>(replay = 1)

    init {
        _craftsmanFlow.tryEmit(_craftsmen.toList())
    }

    override fun getCraftsman(): Flow<List<Craftsman>> = _craftsmanFlow

    override suspend fun addCraftsman(craftsman: Craftsman){
        _craftsmen.add(craftsman)
        _craftsmanFlow.emit(_craftsmen.toList())
    }


}
