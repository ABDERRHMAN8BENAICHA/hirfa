package com.example.hirfa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hirfa.ui.theme.HirfaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HirfaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "nasro",
                        modifier = Modifier.padding(innerPadding)
                    )
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

/**
 * Main composable function for the UI layout
 * @param modifier Modifier for layout adjustments
 */
@Composable
fun FirstUI(modifier: Modifier = Modifier) {
    // TODO 1: Create state variables for text input and items list
//In FirstUI composable
    var textValue by remember { mutableStateOf("") }
    val allItems = remember { mutableStateListOf<String>() }
    var searchQuery by remember { mutableStateOf(allItems.toList()) }
    Column(
        modifier = modifier
            .padding(25.dp)
            .fillMaxSize()
    ) {
        SearchInputBar(
            textValue =textValue, // TODO 2: Connect to state
            onTextValueChange = { text : String ->
                textValue = text    /* TODO 3: Update text state */
                if(textValue.isNotBlank()) {
                    searchQuery = allItems.filter { it.contains(text, ignoreCase = true)}
                    if (searchQuery.any { it.contains(text , ignoreCase = true) }){

                    }else{
                        searchQuery = emptyList()
                        searchQuery += "Not found"
                    }
                }else{
                    if(allItems.isNotEmpty()){
                        searchQuery =allItems
                    }
                }},
            onAddItem = { /* TODO 4: Add item to list */
                if(textValue.isNotBlank()){
                    allItems.add(textValue)
                    searchQuery =allItems
                    textValue = "" }},
            onSearch = {item ->
                if(textValue.isNotBlank()) {
                    searchQuery =  allItems.filter { it.contains(item, ignoreCase = true)}
                    textValue = ""
                }else{
                    searchQuery = allItems.toList()
                }
                /* TODO 5: Implement search functionality */ }
        )

        // TODO 6: Display list of items using CardsList composable
        CardsList( onClear = {
                text : String ->
            for (i in allItems){
                if ( i == text )
                    allItems.remove(i)
            }
        } ,
            searchQuery.toMutableList())
        CardsList( onClear = {
                text : String ->
            for (i in allItems){
                if ( i == text )
                    allItems.remove(i)
            }
        } ,
            searchQuery.toMutableList())

    }
}

/**
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
    onSearch: (String) -> Unit
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
            Button(onClick = { onAddItem(textValue)
                /* TODO 7: Handle add button click */
            }) {
                Text("Add")
            }

            Button(onClick = { /* TODO 8: Handle search button click */ }) {
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
fun CardsList( onClear: (String) -> Unit ,
               displayedItems: MutableList<String>)
{
    // TODO 9: Implement LazyColumn to display items
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        // TODO 10: Create cards for each item in the list
        items(displayedItems) { item ->
            if(item != "Not found")
            {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(text = item, modifier = Modifier
                            .padding(16.dp)
                            .weight(4f)
                        )
                        IconButton(
                            modifier = Modifier
                                .weight(1f),
                            onClick = {
                                onClear(item)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "clear"
                            )
                        }

                    }
                }
            }else{
                Box {
                    Text(text = item, modifier = Modifier
                        .padding(100.dp),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}