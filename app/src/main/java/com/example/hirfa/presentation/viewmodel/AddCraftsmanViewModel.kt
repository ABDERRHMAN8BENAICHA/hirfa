package com.example.hirfa.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hirfa.domain.usecase.AddCraftsmanUseCase
import com.example.hirfa.data.model.Craftsman
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCraftsmanViewModel @Inject constructor(
    private val addCraftsmanUseCase: AddCraftsmanUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddCraftsmanState())
    val state: StateFlow<AddCraftsmanState> = _state

    fun onEvent(event: AddCraftsmanEvent) {
        when (event) {
            is AddCraftsmanEvent.NameChanged -> updateState { it.copy(name = event.name, nameError = null) }
            is AddCraftsmanEvent.DescriptionChanged -> updateState { it.copy(description = event.description, descriptionError = null) }
            is AddCraftsmanEvent.CategorySelected -> updateState { it.copy(category = event.category, categoryError = null) }
            is AddCraftsmanEvent.PhoneNumberChanged -> updateState { it.copy(phoneNumber = event.phoneNumber, phoneNumberError = null) }
            is AddCraftsmanEvent.ProfilePictureChanged -> updateState { it.copy(profilePicture = event.url, profilePictureError = null) }
            is AddCraftsmanEvent.RatingChanged -> updateState { it.copy(rating = event.rating) }
            is AddCraftsmanEvent.Submit -> addCraftsman()
        }
    }

    private fun updateState(update: (AddCraftsmanState) -> AddCraftsmanState) {
        _state.value = update(_state.value)
    }

    private fun addCraftsman() {
        val current = _state.value

        val validationErrors = validateForm(current)
        if (validationErrors.isNotEmpty()) {
            _state.value = current.copy(
                nameError = validationErrors["name"],
                descriptionError = validationErrors["description"],
                categoryError = validationErrors["category"],
                profilePictureError = validationErrors["profilePicture"],
                phoneNumberError = validationErrors["phoneNumber"]
            )
            return
        }

        viewModelScope.launch {
            _state.value = current.copy(isLoading = true, errorMessage = null, successMessage = null)

            val craftsman = Craftsman(
                id = "",
                name = current.name,
                description = current.description,
                category = current.category,
                profilePicture = current.profilePicture,
                urlImage = "",
                phoneNumber = current.phoneNumber,
                rating = current.rating,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )

            val result = addCraftsmanUseCase(craftsman)

            _state.value = if (result) {
                current.copy(
                    isLoading = false,
                    successMessage = "تمت الإضافة بنجاح",
                    errorMessage = null
                )
            } else {
                current.copy(
                    isLoading = false,
                    successMessage = null,
                    errorMessage = "حدث خطأ أثناء الإضافة"
                )
            }
        }
    }

    private fun validateForm(current: AddCraftsmanState): Map<String, String?> {
        val errors = mutableMapOf<String, String?>()

        if (current.name.isBlank()) errors["name"] = "الاسم مطلوب"
        if (current.description.isBlank()) errors["description"] = "الوصف مطلوب"
        if (current.category.isBlank()) errors["category"] = "المجال مطلوب"
        if (current.profilePicture.isBlank()) errors["profilePicture"] = "الصورة مطلوبة"
        if (!isValidPhoneNumber(current.phoneNumber)) errors["phoneNumber"] = "رقم الهاتف غير صالح"

        return errors
    }

    private fun isValidPhoneNumber(phone: String): Boolean {
        return phone.matches(Regex("^\\+?\\d{8,15}\$"))
    }

    sealed class AddCraftsmanEvent {
        data class NameChanged(val name: String) : AddCraftsmanEvent()
        data class DescriptionChanged(val description: String) : AddCraftsmanEvent()
        data class CategorySelected(val category: String) : AddCraftsmanEvent()
        data class ProfilePictureChanged(val url: String) : AddCraftsmanEvent()
        data class PhoneNumberChanged(val phoneNumber: String) : AddCraftsmanEvent()
        data class RatingChanged(val rating: Float) : AddCraftsmanEvent()
        object Submit : AddCraftsmanEvent()
    }
}

data class AddCraftsmanState(
    val name: String = "",
    val nameError: String? = null,
    val description: String = "",
    val descriptionError: String? = null,
    val phoneNumber: String = "",
    val phoneNumberError: String? = null,
    val category: String = "",
    val categoryError: String? = null,
    val profilePicture: String = "",
    val profilePictureError: String? = null,
    val rating: Float = 0f,
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)
