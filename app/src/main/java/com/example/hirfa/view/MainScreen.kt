package com.example.hirfa.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.hirfa.R
import com.example.hirfa.model.Category
import com.example.hirfa.model.Craftsman
import com.example.hirfa.repository.CategoryRepository
import com.example.hirfa.repository.CraftsmanRepository
import com.example.hirfa.viewmodel.CategoryViewModel
import com.example.hirfa.viewmodel.CraftsmanViewModel

@Composable
fun MainScreen(
    categoryViewModel: CategoryViewModel,
    craftsmanViewModel: CraftsmanViewModel,
    modifier: Modifier = Modifier
) {
    val categoryState by categoryViewModel.categoryState.collectAsState()
    val craftsmanState by craftsmanViewModel.uiState.collectAsState()

    Column(modifier = modifier.padding(10.dp)) {

        Spacer(modifier = Modifier.width(40.dp))
        Toolbar()
        Spacer(modifier = Modifier.height(15.dp))

        SearchView()
        Spacer(modifier = Modifier.height(15.dp))

        ServiceText()
        CategoryList(categories = categoryState.categories)

        Spacer(modifier = Modifier.height(20.dp))

        SliderImages()
        Spacer(modifier = Modifier.height(10.dp))

        CarftsmenText()
        when {
            craftsmanState.isLoading -> CircularProgressIndicator()
            craftsmanState.error != null -> Text("Error: ${craftsmanState.error}")
            else -> CraftsmanList(craftsmen = craftsmanState.craftsmen)
        }
    }
}

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
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(painter = painterResource(id = category.icon), contentDescription = null, modifier = Modifier.size(54.dp).clip(shape = RoundedCornerShape(5.dp)), contentScale = ContentScale.Crop)
                    }
                }
                Column() {
                    Text(category.name, style = MaterialTheme.typography.titleMedium)
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}

@Composable
fun ServiceText(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Service",
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp)

        Spacer(modifier = Modifier.width(5.dp))

        IconButton(
            modifier = Modifier.size(25.dp)
                .clickable { true }
                .clip(CircleShape),
            onClick = { null }
        ) {
            Image(painter = painterResource(R.drawable.forward), contentDescription = null)
        }
    }
}

@Composable
fun CarftsmenText(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Craftsmen",
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp)

        Spacer(modifier = Modifier.width(5.dp))

        IconButton(
            modifier = Modifier.size(25.dp)
                .clickable { true }
                .clip(CircleShape),
            onClick = { null }
        ) {
            Image(painter = painterResource(R.drawable.forward), contentDescription = null)
        }
    }
}

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
                        Row(modifier = Modifier.fillParentMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
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

@Composable
fun Toolbar(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable { true }
                    .padding(5.dp)
            )
            Row(modifier = Modifier.width(70.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    alignment = Alignment.Center,
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .clickable { true }
                        .padding(5.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.setting),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .clickable { true }
                        .padding(5.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text("Hirfa Platform",
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 23.sp)
    }
}

@Composable
fun SearchView(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text("Search Hirfa") },
        trailingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "menu",
                modifier = Modifier.clickable { /* some action */ }
            )
        },
        shape = RoundedCornerShape(25.dp)
    )
}

@Composable
fun SliderImages(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Services Gallery",
                modifier = Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp)

            Spacer(modifier = Modifier.width(5.dp))

            IconButton(
                modifier = Modifier.size(25.dp)
                    .clickable { true }
                    .clip(CircleShape),
                onClick = { null }
            ) {
                Image(painter = painterResource(R.drawable.forward), contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(modifier = Modifier.fillMaxWidth().height(150.dp).padding(2.dp)) {
            item {
                Spacer(modifier = Modifier.width(10.dp))
                Card(elevation = CardDefaults.cardElevation(4.dp)) {
                    Image(
                        modifier = Modifier.width(250.dp).height(150.dp),
                        painter = painterResource(R.drawable.woodworking),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.width(12.dp))
                }
            }
            item {
                Spacer(modifier = Modifier.width(10.dp))
                Card(elevation = CardDefaults.cardElevation(4.dp)) {
                    Image(
                        modifier = Modifier.width(250.dp).height(150.dp),
                        painter = painterResource(R.drawable.cleaning),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.width(12.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun CraftsmanPreview(){

    CraftsmanList(craftsmen = CraftsmanRepository.getAllCraftsman())
}
