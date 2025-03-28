package com.example.hirfa.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hirfa.R
import com.example.hirfa.data.model.Craftsman


@Composable
fun CraftsmanList(craftsmen: List<Craftsman>) {
    LazyRow {
        items(craftsmen) { craftsman ->
            Spacer(modifier = Modifier.width(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(modifier = Modifier.padding(10.dp)) {
                    AsyncImage(
                        model = craftsman.profilePicture,
                        contentDescription = null,
                        modifier = Modifier
                            .size(84.dp)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(craftsman.name, style = MaterialTheme.typography.titleMedium)
                        Text(craftsman.description, style = MaterialTheme.typography.bodyMedium)
                        Text("Category: ${craftsman.category}", style = MaterialTheme.typography.bodySmall)
                        Row (modifier = Modifier.fillParentMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween){
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("${craftsman.rating}", style = MaterialTheme.typography.bodySmall)
                            }


                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.play),
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                        .clickable { true }
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}