package com.example.hirfa.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hirfa.data.model.Category

@Composable
fun CategoryList(categories: List<Category>) {
    Spacer(modifier = Modifier.size(10.dp))

    LazyRow {

        items(categories) { category ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Card(
                    shape = RoundedCornerShape(110.dp),
                    modifier = Modifier
                        .width(80.dp)
                        .height(85.dp)
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column (modifier = Modifier
                        .padding(16.dp)
                        .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = category.icon),
                            contentDescription = null,
                            modifier = Modifier.size(54.dp).clip(shape = RoundedCornerShape(5.dp)),
                            contentScale = ContentScale.Crop

                        )
                        //Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                Column() {
                    Text(category.name, style = MaterialTheme.typography.titleMedium)
                    // Text(category.description, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}