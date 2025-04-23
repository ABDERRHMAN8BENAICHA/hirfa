package com.example.hirfa.domain.usecase

import com.example.hirfa.data.model.Craftsman
import com.example.hirfa.data.repository.CraftsmanRepository
import javax.inject.Inject

class AddCraftsmanUseCase @Inject constructor(private val craftsmanRepository: CraftsmanRepository) {
    suspend operator fun invoke(craftsman: Craftsman): Boolean {
        return craftsmanRepository.addCraftsman(craftsman)
    }
}
