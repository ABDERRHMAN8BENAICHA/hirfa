# Task 4: Cloud Integration and Application Completion

## Objective

Complete the Hirfa application by integrating cloud storage and implementing full CRUD functionality for craftsmen.

## Part 1: Backend as a Service (BaaS) Integration

In this part, you will integrate a cloud-based backend service to store and retrieve application data.

### Requirements:

1.  Choose and integrate either Firebase or Supabase as your BaaS solution.
2.  Implement the following cloud features:
    *   Remote data storage for craftsmen.
    *   Real-time data synchronization (optional, but recommended).
    *   Proper error handling for network operations.
3.  Update the existing repository implementations (CraftsmanRepositoryImpl.kt) to work with the cloud service.
4.  Ensure basic offline functionality (e.g., caching the last fetched list).

### Implementation Steps:

1.  **Configure BaaS Service**
    *   Set up a project in the Firebase Console or Supabase Dashboard.
    *   Add necessary dependencies (e.g., Firebase Firestore/Realtime Database or Supabase Kotlin client) to your build.gradle.kts.
    *   Initialize the service in your application class (HirfaApp.kt) or using Hilt modules.
2.  **Refactor Repository Layer**
    *   Create a remote data source interface and implementation for interacting with the BaaS.
    *   Update the CraftsmanRepositoryImpl.kt to fetch data primarily from the remote source, potentially falling back to or combining with local data/cache.
    *   Inject the remote data source into the repository using Hilt.
    *   Implement logic to fetch data from the cloud. Consider how to handle updates (e.g., fetching fresh data on app start or using real-time listeners).
3.  **Update ViewModels**
    *   Modify existing ViewModels (CraftsmanViewModel.kt, AddCraftsmanViewModel) to handle asynchronous cloud operations (fetching, adding, updating, deleting).
    *   Use Kotlin Coroutines and Flows to manage asynchronous tasks.
    *   Update UI states (`CraftsmanListState`, `AddCraftsmanState`) to include loading indicators and error messages.

## Part 2: Complete Application Functionality

In this part, you will implement the remaining CRUD operations and enhance search functionality for craftsmen.

### Requirements:

1.  Complete craftsman management features:
    *   **Create:** Finish the Add Craftsman functionality (AddCraftsmanScreen.kt) to save data to the BaaS.
    *   **Read:** Implement a craftsman details view/screen (potentially navigating from the list).
    *   **Update:** Add the ability to edit existing craftsmen details.
    *   **Delete:** Implement craftsman removal functionality (e.g., a delete button on the details screen or list item).
2.  Implement search functionality:
    *   Search by craftsman name (as currently implemented in MainScreen.kt, ensure it works with cloud data).
    *   Filter by categories (using the existing category chips/logic in MainScreen.kt, ensure it works with cloud data).

### Implementation Steps:

1.  **Complete Craftsman Management UI & Logic**
    *   Finalize the AddCraftsmanScreen.kt to collect all necessary craftsman details (Craftsman.kt) and trigger the save operation in the ViewModel.
    *   Create an "Edit Craftsman" screen, potentially reusing parts of the Add screen, pre-filled with existing data.
    *   Implement navigation to the Edit screen.
    *   Add delete functionality (e.g., a button) and include a confirmation dialog before deleting.
    *   Implement the corresponding `updateCraftsman` and `deleteCraftsman` functions in the ViewModel, Repository, and Remote Data Source.
    *   (Optional) Create a dedicated Craftsman Detail screen if more information needs to be displayed than what's on the list card.
2.  **Refine Search/Filter Functionality**
    *   Ensure the search and category filtering logic in CraftsmanViewModel.kt works correctly with the data fetched from the BaaS. If fetching all data and filtering client-side, this might be sufficient. If the dataset becomes large, consider implementing server-side filtering/querying via the BaaS API.
3.  **User Experience**
    *   Display loading indicators (e.g., `CircularProgressIndicator`) in the UI (MainScreen.kt, AddCraftsmanScreen.kt, Edit Screen) while data is being fetched or modified.
    *   Show user-friendly error messages (e.g., using `Snackbar` or dedicated error text components) when cloud operations fail.
    *   Consider adding simple animations or transitions for a smoother feel.

## Bonus Challenge: User Authentication and Data Relation

### Requirements:

1.  Implement user authentication using your chosen BaaS (Firebase Auth or Supabase Auth):
    *   Registration screen/flow.
    *   Login screen/flow.
    *   Logout functionality.
    *   (Optional) Password reset feature.
2.  Associate craftsmen data with users:
    *   Modify the Craftsman.kt data model to include a `userId` field.
    *   Ensure that when a craftsman is added or updated, the current user's ID is stored.
    *   Implement basic authorization: potentially restrict editing/deleting craftsmen to the user who created them (requires adjusting repository queries and UI logic).
3.  (Optional) Add user profiles:
    *   A screen displaying basic user information.
    *   A view showing the craftsmen added by the logged-in user.

### Implementation Steps:

1.  **Set Up Authentication**
    *   Configure authentication providers (e.g., Email/Password) in your BaaS console.
    *   Add BaaS authentication dependencies.
    *   Create Login and Registration Composable screens.
    *   Implement ViewModels to handle authentication logic (calling BaaS SDK functions).
    *   Manage user sessions (e.g., observe auth state changes) and update the UI accordingly (e.g., navigate to main screen on login, show login screen on logout).
    *   Provide a logout mechanism (e.g., a button in a profile screen or menu).
2.  **Update Craftsman Logic for User Relation**
    *   Add a `userId: String? = null` field to the Craftsman.kt data class.
    *   Modify the `addCraftsman` and `updateCraftsman` logic in the repository/ViewModel to include the logged-in user's ID.
    *   Adjust data fetching logic if needed (e.g., fetching only the current user's craftsmen for a specific profile view).
    *   Implement permission checks in the UI or ViewModel before allowing edit/delete operations (e.g., compare the craftsman's `userId` with the current user's ID).

## Deliverables

1.  A fully functional Hirfa application integrated with Firebase or Supabase.
2.  Complete CRUD (Create, Read, Update, Delete) operations for craftsmen stored in the cloud.
3.  Working search (by name) and filter (by category) functionality operating on cloud data.
4.  (Bonus) User authentication (Register, Login, Logout) and craftsmen data associated with users.

## Evaluation Criteria

*   **Code Quality & Architecture:** Clean, maintainable code following MVVM principles; proper use of Kotlin, Coroutines, Flow, Compose, and Hilt.
*   **Cloud Integration:** Correct setup and usage of the chosen BaaS (Firebase/Supabase) for data storage and potentially authentication. Robust handling of asynchronous operations.
*   **Functionality:** All required CRUD and search/filter features are implemented correctly.
*   **User Experience:** Intuitive UI, appropriate loading states, clear error handling, smooth navigation.
*   **Error Handling:** Graceful handling of network errors, data validation errors, and other potential issues.
*   **(Bonus) Authentication:** Secure and correct implementation of user authentication and data ownership/permissions.

Good luck!