package com.example.hirfa.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hirfa.presentation.view.components.*
import com.example.hirfa.presentation.viewmodel.CategoryViewModel
import com.example.hirfa.presentation.viewmodel.CraftsmanViewModel

@Composable
fun MainScreen(
    categoryViewModel: CategoryViewModel,
    craftsmanViewModel: CraftsmanViewModel,
    modifier: Modifier = Modifier
) {
    val categoryState by categoryViewModel.categoryState.collectAsState()
    val craftsmanState by craftsmanViewModel.uiState.collectAsState()

    Column(modifier = modifier.padding(10.dp)) {
        // Toolbar
        Toolbar()
        Spacer(modifier = Modifier.height(15.dp))

        // Search View
        SearchView()
        Spacer(modifier = Modifier.height(15.dp))

        // Categories Section
        ServiceText()
        when {
            categoryState.isLoading -> CircularProgressIndicator()
            categoryState.errorMessage != null -> Text("Error: ${categoryState.errorMessage}") // ✅ Ensure correct property
            else -> CategoryList(categories = categoryState.categories)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Slider Images
        SliderImages()
        Spacer(modifier = Modifier.height(10.dp))

        // Craftsmen Section
        CarftsmenText()
        when {
            craftsmanState.isLoading -> CircularProgressIndicator()
            craftsmanState.errorMessage != null -> Text("Error: ${craftsmanState.errorMessage}") // ✅ Ensure correct property

            else -> CraftsmanList(craftsmen = craftsmanState.craftsmen)
        }
    }
}
