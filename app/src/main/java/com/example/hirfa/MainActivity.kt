package com.example.Hirfa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hirfa.ui.theme.HirfaTheme
import androidx.compose.material3.BadgedBox
 import androidx.compose.material3.Icon

 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HirfaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
 //                    Greeting(
 //                        name = "Ali Atoussi",
 //                        modifier = Modifier.padding(innerPadding)
 //                    )
                    FirstUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
 }

 @Composable
 fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
 }

 /
 * Main composable function for the UI layout
 * @param modifier Modifier for layout adjustments
 */
 @Composable
 fun FirstUI(modifier: Modifier = Modifier) {
    var input by remember {
        mutableStateOf("")
    }
    val items = remember {
        mutableStateListOf<String>()
    }
    var search by remember {
        mutableStateOf("")
    }

    val displayedItems = if (search.isEmpty()) {
        items
    } else {
        items.filter { it.contains(search, ignoreCase = true) }
    }
    Column(
        modifier = modifier
            .padding(25.dp)
            .fillMaxSize()
    ) {
        SearchInputBar(
            textValue = input,
            onTextValueChange = {
                newValue -> input = newValue
            },
            onAddItem = {
                if (input.isNotBlank()) {
                items.add(input)
                input = ""
            } },
            onSearch = {
                search = input
            },
            count = items.size
        )
        CardsList(displayedItems = displayedItems)
    }
 }

 /
 * Composable for search and input controls
 * @param textValue Current value of the input field
 * @param onTextValueChange Callback for text changes
 * @param onAddItem Callback for adding new items
 * @param onSearch Callback for performing search
 */
 @Composable
 fun SearchInputBar(
    textValue: String,
    onTextValueChange: (String) -> Unit,
    onAddItem: (String) -> Unit,
    onSearch: (String) -> Unit,
    count: Int
     ) {
    Column {
        TextField(
            value = textValue,
            onValueChange = onTextValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter text...") }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {onAddItem(textValue)}) {
                Text("Add")
            }
            BadgedBox(
                badge = {
                            Text("$count")
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Number of Items",
                )
            }
            Button(onClick = { onSearch(textValue) }) {
                Text("Search")
            }
        }
    }
 }

 /**
 * Composable for displaying a list of items in cards
 * @param displayedItems List of items to display
 */

 @Composable
 fun CardsList(displayedItems: List<String>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(displayedItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = item,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}