package com.example.hirfa.domain.usecase

import com.example.hirfa.data.model.Craftsman
import com.example.hirfa.data.repository.CraftsmanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCraftsmenUseCase @Inject constructor(
    private val craftsmanRepository: CraftsmanRepository
) {
    fun execute(): Flow<List<Craftsman>> {
        return craftsmanRepository.getCraftsman()
    }
}
