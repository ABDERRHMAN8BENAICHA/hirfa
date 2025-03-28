package com.example.hirfa.data.repository

import com.example.hirfa.data.model.Craftsman
import kotlinx.coroutines.flow.Flow

interface CraftsmanRepository {

    fun getCraftsman(): Flow<List<Craftsman>>

    suspend fun addCraftsman(craftsman: Craftsman)

}
