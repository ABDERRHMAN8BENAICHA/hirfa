package com.example.hirfa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hirfa.model.Craftsman
import com.example.hirfa.repository.CraftsmanRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class UIState(
    val isLoading: Boolean = false,
    val craftsmen: List<Craftsman> = emptyList(),
    val error: String? = null
)

class CraftsmanViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        loadServices()
    }

    private fun loadServices() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                delay(2000)
                val craftsmen = CraftsmanRepository.getAllCraftsman()
                _uiState.value = _uiState.value.copy(craftsmen = craftsmen,error=null)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}
