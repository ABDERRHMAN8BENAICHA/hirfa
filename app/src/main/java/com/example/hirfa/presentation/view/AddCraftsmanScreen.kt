//package com.example.hirfa.presentation.view
//
//import android.net.Uri
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.outlined.Add
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import coil.compose.rememberAsyncImagePainter
//import com.example.hirfa.presentation.viewmodel.AddCraftsmanViewModel
//
//
//@Composable
//fun AddCraftsmanScreen(viewModel: AddCraftsmanViewModel = viewModel()) {
//    val state by viewModel.state.collectAsState()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .verticalScroll(rememberScrollState()),
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Spacer(Modifier.height(50.dp))
//        Text("إضافة حرفي جديد", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
//
//        PickImage(viewModel)
//
//        OutlinedTextField(
//            value = state?.name ?: "",
//            onValueChange = { viewModel.onEvent(AddCraftsmanViewModel.AddCraftsmanEvent.NameChanged(it)) },
//            placeholder = { Text("اسم الحرفي", textAlign = TextAlign.End) },
//            modifier = Modifier.fillMaxWidth(),
//            singleLine = true
//        )
//
//        OutlinedTextField(
//            value = state?.description ?: "",
//            onValueChange = { viewModel.onEvent(AddCraftsmanViewModel.AddCraftsmanEvent.DescriptionChanged(it)) },
//            placeholder = { Text("نبذة عن الحرفي", textAlign = TextAlign.End) },
//            modifier = Modifier.fillMaxWidth(),
//        )
//
//        CraftsmanCategoryDropdown(viewModel)
//
//        OutlinedTextField(
//            value = state?.phoneNumber ?: "",
//            onValueChange = { viewModel.onEvent(AddCraftsmanViewModel.AddCraftsmanEvent.PhoneNumberChanged(it)) },
//            placeholder = { Text("رقم الهاتف", textAlign = TextAlign.End) },
//            modifier = Modifier.fillMaxWidth(),
//            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
//            singleLine = true
//        )
//
//        Button(
//            onClick = { viewModel.onEvent(AddCraftsmanViewModel.AddCraftsmanEvent.Submit) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(55.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.White),
//            enabled = !(state?.isLoading ?: false)
//        ) {
//            Text(if (state?.isLoading == true) "إضافة..." else "إضافة حرفي", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//        }
//    }
//}
//
//@Composable
//fun PickImage(viewModel: AddCraftsmanViewModel) {
//    val imageUri = viewModel.state.collectAsState().value.profilePicture
//    val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
//        uri?.let { viewModel.onEvent(AddCraftsmanViewModel.AddCraftsmanEvent.ProfilePictureChanged(it)) }
//    }
//
//    Box(
//        modifier = Modifier
//            .size(170.dp)
//            .clip(RoundedCornerShape(20.dp))
//            .border(BorderStroke(2.dp, Color.Gray), shape = RoundedCornerShape(20.dp))
//            .clickable { pickImageLauncher.launch("image/*") },
//        contentAlignment = Alignment.Center
//    ) {
//        imageUri?.let { uri ->
//            Image(
//                painter = rememberAsyncImagePainter(uri),
//                contentDescription = "Profile Picture",
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.FillHeight
//            )
//        } ?: Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Icon(Icons.Outlined.Add, contentDescription = "Add Image", tint = Color.Gray)
//            Text("إضافة صورة", fontSize = 16.sp, color = Color.Gray)
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CraftsmanCategoryDropdown(viewModel: AddCraftsmanViewModel) {
//    val options = listOf("نجار", "حداد", "كهربائي", "سباك", "بناء", "مصلح سيارات")
//    var expanded by remember { mutableStateOf(false) }
//    val selectedOption = viewModel.state.collectAsState().value.category ?: ""
//
//    ExposedDropdownMenuBox(
//        expanded = expanded,
//        onExpandedChange = { expanded = it }
//    ) {
//        OutlinedTextField(
//            value = selectedOption,
//            onValueChange = {},
//            readOnly = true,
//            placeholder = { Text("مجال الحرفة", textAlign = TextAlign.End) },
//            trailingIcon = {
//                Icon(imageVector = Icons.Outlined.Add, contentDescription = "Expand")
//            },
//            singleLine = true,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        ExposedDropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            options.forEach { option ->
//                DropdownMenuItem(
//                    text = { Text(option) },
//                    onClick = {
//                        viewModel.onEvent(AddCraftsmanViewModel.AddCraftsmanEvent.CategorySelected(option))
//                        expanded = false
//                    }
//                )
//            }
//        }
//    }
//}

package com.example.hirfa.presentation.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.hirfa.presentation.viewmodel.AddCraftsmanViewModel

@Composable
fun AddCraftsmanScreen(viewModel: AddCraftsmanViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))

        Text(
            text = "Add New Craftsman",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        PickImage(viewModel)

        OutlinedTextField(
            value = state.name,
            onValueChange = {
                viewModel.onEvent(AddCraftsmanViewModel.AddCraftsmanEvent.NameChanged(it))
            },
            placeholder = { Text("Craftsman Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = state.description,
            onValueChange = {
                viewModel.onEvent(AddCraftsmanViewModel.AddCraftsmanEvent.DescriptionChanged(it))
            },
            placeholder = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
        )

        CraftsmanCategoryDropdown(viewModel)

        OutlinedTextField(
            value = state.phoneNumber,
            onValueChange = {
                viewModel.onEvent(AddCraftsmanViewModel.AddCraftsmanEvent.PhoneNumberChanged(it))
            },
            placeholder = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            singleLine = true
        )

        Button(
            onClick = { viewModel.onEvent(AddCraftsmanViewModel.AddCraftsmanEvent.Submit) },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            enabled = !state.isLoading
        ) {
            Text(
                text = if (state.isLoading) "Adding..." else "Add Craftsman",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (!state.errorMessage.isNullOrEmpty()) {
            Text(
                text = state.errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        if (!state.successMessage.isNullOrEmpty()) {
            Text(
                text = state.successMessage ?: "",
                color = Color.Green,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PickImage(viewModel: AddCraftsmanViewModel) {
    val imageUri = viewModel.state.collectAsState().value.profilePicture
    val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { viewModel.onEvent(AddCraftsmanViewModel.AddCraftsmanEvent.ProfilePictureChanged(it.toString())) }
    }

    Box(
        modifier = Modifier
            .size(170.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(BorderStroke(2.dp, Color.Gray), shape = RoundedCornerShape(20.dp))
            .clickable { pickImageLauncher.launch("image/*") },
        contentAlignment = Alignment.Center
    ) {
        if (imageUri.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(imageUri),
                contentDescription = "Profile Picture",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Outlined.Add, contentDescription = "Add Image", tint = Color.Gray, modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.height(4.dp))
                Text("Add Image", fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CraftsmanCategoryDropdown(viewModel: AddCraftsmanViewModel) {
    val options = listOf("Carpenter", "Blacksmith", "Electrician", "Plumber", "Builder", "Mechanic")
    var expanded by remember { mutableStateOf(false) }
    val selectedOption = viewModel.state.collectAsState().value.category

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            placeholder = { Text("Craft Field") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            singleLine = true,
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
                .clickable { expanded = true }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        expanded = false
                        viewModel.onEvent(AddCraftsmanViewModel.AddCraftsmanEvent.CategorySelected(option))
                    }
                )
            }
        }
    }
}
