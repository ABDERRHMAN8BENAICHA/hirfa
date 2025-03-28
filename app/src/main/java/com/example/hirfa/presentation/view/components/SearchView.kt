package com.example.hirfa.presentation.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SearchView(modifier: Modifier = Modifier){
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = {n -> text =n},
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text("search hirfa") },
        trailingIcon ={ Icon(imageVector = Icons.Filled.Search,
            contentDescription = "search",

            )
        },

        leadingIcon = {
            Icon(imageVector = Icons.Filled.Menu,
            contentDescription = "manu",
            modifier = Modifier.clickable { true }
        )
        },
        shape = RoundedCornerShape(25.dp),
        colors = TextFieldDefaults.colors(focusedIndicatorColor = Color.Gray,
            focusedLabelColor = Color.Gray,
            cursorColor = Color.Black)
    )

}