# Task 3: Understanding Dependency Injection and Implementing Add Craftsman Feature

## Dependency Injection with Dagger Hilt

The Hirfa app now uses Dagger Hilt for dependency injection across all layers of the application. Here's how it's structured:

### 1. Data Layer
- Repository implementations are provided via Hilt modules
- Data sources and their dependencies are injected into repositories
- Example: `CraftsmanRepository` and `CategoryRepository` are injected with their dependencies

### 2. Domain Layer
- Use cases are injected with their required repositories
- Examples:
  - `GetAllCraftsmenUseCase` is injected with `CraftsmanRepository`
  - `AddCraftsmanUseCase` is injected with `CraftsmanRepository`
  - `GetAllCategoriesUseCase` is injected with `CategoryRepository`

### 3. Presentation Layer
- ViewModels are annotated with `@HiltViewModel`
- Use cases are injected into ViewModels
- Example: `AddCraftsmanViewModel` is injected with `AddCraftsmanUseCase`

## Your Task: Implement Add Craftsman Feature

### 1. Create Add Craftsman View
Create a new composable function `AddCraftsmanView` in the presentation layer with the following requirements:

- Input fields for:
  - Craftsman name
  - Description
  - Category selection (dropdown)
  - Profile picture URL
  - Phone number
  - Rating (optional, default to 0.0)
- Validation for all fields
- Submit button
- Success/Error feedback

### 2. Enhance AddCraftsmanViewModel

Update the `AddCraftsmanViewModel` to include:

```kotlin
// State for form fields
data class AddCraftsmanState(
    val name: String = "",
    val description: String = "",
    val category: String = "",
    val profilePicture: String = "",
    val phoneNumber: String = "",
    val rating: Float = 0.0f,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

// Events that can be triggered
sealed class AddCraftsmanEvent {
    data class NameChanged(val name: String) : AddCraftsmanEvent()
    data class DescriptionChanged(val description: String) : AddCraftsmanEvent()
    data class CategorySelected(val category: String) : AddCraftsmanEvent()
    data class ProfilePictureChanged(val url: String) : AddCraftsmanEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : AddCraftsmanEvent()
    data class RatingChanged(val rating: Float) : AddCraftsmanEvent()
    object Submit : AddCraftsmanEvent()
}
```

Required functionality:
1. State management for form fields
2. Input validation:
   - Name must not be empty
   - Description must not be empty
   - Category must be selected
   - Phone number must be valid
   - Profile picture URL must be valid
3. Error handling
4. Integration with `AddCraftsmanUseCase`
5. Success/failure feedback

### Implementation Steps

1. **AddCraftsmanViewModel Implementation**
   - Add state management using `StateFlow`
   - Implement event handling
   - Add validation logic
   - Integrate with `AddCraftsmanUseCase`

2. **AddCraftsmanView Implementation**
   - Create UI components using Jetpack Compose
   - Connect UI events to ViewModel
   - Show loading state
   - Display error messages
   - Show success confirmation

3. **Navigation Integration**
   - Add navigation to Add Craftsman screen
   - Handle back navigation
   - Pass results back to previous screen

### Success Criteria

Your implementation should:
1. Successfully add new craftsmen to the repository
2. Validate all input fields
3. Show appropriate loading states
4. Handle and display errors
5. Provide success feedback
6. Follow MVVM architecture patterns
7. Properly utilize dependency injection

## Tips
- Use `viewModelScope` for coroutines in ViewModel
- Implement proper error handling
- Follow Material Design guidelines for the UI
- Use proper state management
- Make use of Hilt's dependency injection

## Bonus Challenges
1. Add image upload functionality instead of just URL
2. Implement form state persistence
3. Add unit tests for ViewModel
4. Add UI tests for the Add Craftsman screen
5. Add location selection for craftsman
6. Implement rating validation (0.0 to 5.0)

## Add Craftsman UI Design

The Add Craftsman screen should follow the app's existing Material Design 3 theme and match the overall aesthetic of the main screen. Key UI elements should include:

- A top app bar with a back button
- Material 3 text fields for input
- A dropdown menu for category selection
- A preview of the profile picture
- A prominent submit button
- Loading indicator during submission
- Success/error messages using Material Design snackbars

Good luck!
