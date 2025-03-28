package com.example.hirfa.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hirfa.data.model.Craftsman
import com.example.hirfa.domain.usecase.GetCraftsmenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UI state for displaying craftsmen data
 */
data class UIState(
    val isLoading: Boolean = false,
    val craftsmen: List<Craftsman> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class CraftsmanViewModel @Inject constructor(
    private val getCraftsmenUseCase: GetCraftsmenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        loadCraftsmen()
    }

    fun loadCraftsmen() {
        viewModelScope.launch {
            _uiState.value = UIState(isLoading = true)

            getCraftsmenUseCase()
                .onEach { craftsmen ->
                    _uiState.value = UIState(craftsmen = craftsmen, isLoading = false)
                }
                .catch { e ->
                    _uiState.value = UIState(error = "Failed to load craftsmen: ${e.localizedMessage}", isLoading = false)
                }
                .launchIn(this)
        }
    }
}
