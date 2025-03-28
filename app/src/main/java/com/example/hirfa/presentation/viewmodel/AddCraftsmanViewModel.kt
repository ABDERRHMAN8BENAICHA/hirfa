package com.example.hirfa.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hirfa.data.model.Craftsman
import com.example.hirfa.domain.usecase.AddCraftsmanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCraftsmanViewModel @Inject constructor(
    private val addCraftsmanUseCase: AddCraftsmanUseCase
) : ViewModel() {

    // Holds the list of craftsmen
    private val _craftsmen = MutableStateFlow<List<Craftsman>>(emptyList())
    val craftsmen: StateFlow<List<Craftsman>> get() = _craftsmen

    // Holds the loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    // Event Flow for UI interactions
    private val _eventFlow = MutableSharedFlow<AddCraftsmanEvent>()
    val eventFlow: SharedFlow<AddCraftsmanEvent> get() = _eventFlow

    // Function to handle events
    fun onEvent(event: AddCraftsmanEvent) {
        when (event) {
            is AddCraftsmanEvent.AddCraftsman -> addCraftsman(event.craftsman)
            is AddCraftsmanEvent.Error -> {} // Handle error event
            is AddCraftsmanEvent.Success -> {} // Handle success event
        }
    }

    // Function to add a new craftsman
    private fun addCraftsman(craftsman: Craftsman) {
        viewModelScope.launch {
            _isLoading.value = true

            try {
                addCraftsmanUseCase.invoke(craftsman) // No need to store result if it's Unit
                _craftsmen.update { it + craftsman } // Update state safely
                _eventFlow.emit(AddCraftsmanEvent.Success("Craftsman added successfully!"))
            } catch (e: Exception) {
                _eventFlow.emit(AddCraftsmanEvent.Error("An error occurred: ${e.localizedMessage}"))
            } finally {
                _isLoading.value = false
            }
        }
    }
}

// Sealed class to handle events
sealed class AddCraftsmanEvent {
    data class AddCraftsman(val craftsman: Craftsman) : AddCraftsmanEvent()
    data class Success(val message: String) : AddCraftsmanEvent()
    data class Error(val message: String) : AddCraftsmanEvent()
}
