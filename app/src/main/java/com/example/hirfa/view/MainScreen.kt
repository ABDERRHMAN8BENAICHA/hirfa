package com.example.hirfa.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Label

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hirfa.MainActivity.BottomNavBarItems
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

        //Display toolbar
        Spacer(modifier = Modifier.width(40.dp))
        Toolbar()
        Spacer(modifier = Modifier.height(0.dp))
        // Display search view
        SearchView()
        Spacer(modifier = Modifier.height(15.dp))
        // Display categories
        ServiceText()
        CategoryList(categories = categoryState.categories)

        Spacer(modifier = Modifier.height(5.dp))
        //Slider Images
        SliderImages()
        Spacer(modifier = Modifier.height(5.dp))
        // Display craftsmen
        CarftsmenText()
       // Text("Craftsmen", style = MaterialTheme.typography.headlineMedium)
        when {
            craftsmanState.isLoading -> CircularProgressIndicator()
            craftsmanState.error != null -> Text("Error: ${craftsmanState.error}")
            else -> CraftsmanList(craftsmen = craftsmanState.craftsmen)
        }
    }
}

@Composable
fun CategoryList(categories: List<Category>) {
Spacer(modifier = Modifier.size(0.dp))

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
//@Preview
@Composable
fun ServiceText(modifier: Modifier = Modifier){
Row (verticalAlignment = Alignment.CenterVertically){

    Text("Service",
        modifier = Modifier.padding(start = 10.dp),
        style = MaterialTheme.typography.titleMedium,
        fontSize = 20.sp)

    Spacer(modifier = Modifier.width(5.dp))

    IconButton(modifier = Modifier.size(25.dp)
        .clickable { true }
        .clip(CircleShape),
        onClick = {null}
    ) {
        Image(painter = painterResource(R.drawable.forward),
            contentDescription = null)
    }


}
}
@Preview
@Composable
fun CarftsmenText(modifier: Modifier = Modifier){
    Row (verticalAlignment = Alignment.CenterVertically){

        Text("Employees",
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp)

        Spacer(modifier = Modifier.width(5.dp))

        IconButton(modifier = Modifier.size(25.dp)
            .clickable { true }
            .clip(CircleShape),
            onClick = {null}
        ) {
            Image(painter = painterResource(R.drawable.forward),
                contentDescription = null)
        }


    }
}
@Composable
fun CraftsmanList(craftsmen: List<Craftsman>) {
    LazyColumn {
        items(craftsmen) { craftsman ->
            Spacer(modifier = Modifier.width(2.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        // Image column with rating
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = craftsman.profilePicture,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(84.dp)
                                    .clip(MaterialTheme.shapes.medium),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            // Star and rating
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("${craftsman.rating}", style = MaterialTheme.typography.bodySmall)
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // Craftsman details
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(craftsman.name, style = MaterialTheme.typography.titleMedium)
                            Text(craftsman.description, style = MaterialTheme.typography.bodyMedium)
                            Text("Category: ${craftsman.category}", style = MaterialTheme.typography.bodySmall)

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.play),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(16.dp)
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

//@Preview
@Composable
fun Toolbar(modifier: Modifier = Modifier){
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween ) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable { true }
                    .padding(5.dp)
            )
            Row(modifier = Modifier.width(70.dp),
                verticalAlignment = Alignment.CenterVertically) {


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
                //  Spacer(modifier = Modifier.width(1.dp))
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
        Spacer(modifier = Modifier.height(0.dp))
        Text("َAli Atoussi",
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 23.sp)
    }


}

//@Preview
@Composable
fun SearchView(modifier: Modifier = Modifier){
    var text by remember { mutableStateOf("") }
OutlinedTextField(
     value = text,
    onValueChange = {n -> text =n},
    modifier = Modifier.fillMaxWidth(),
    singleLine = true,
    label = { Text("search ") },
    trailingIcon ={ Icon(imageVector = Icons.Filled.Search,
        contentDescription = "search",

            )},

    leadingIcon = {Icon(imageVector = Icons.Filled.Menu,
        contentDescription = "manu",
        modifier = Modifier.clickable { true }
    )},
    shape = RoundedCornerShape(25.dp),
    colors = TextFieldDefaults.colors(focusedIndicatorColor = Color.Gray,
        focusedLabelColor = Color.Gray,
        cursorColor = Color.Black)
        )

}


//@Preview
@Composable
fun categoriesPreview() {
    val categories : List<Category> = CategoryRepository.getAllCategories()
    CategoryList(categories = categories)

}


@Composable
fun SliderImages(modifier: Modifier=Modifier){
   Column(modifier = Modifier.fillMaxWidth().wrapContentHeight()){
       Row (modifier=Modifier.fillMaxWidth() ,
           verticalAlignment = Alignment.CenterVertically){
           Text("Services Gallery",
               modifier = Modifier.padding(start = 10.dp),
               style = MaterialTheme.typography.titleMedium,
               fontSize = 20.sp)

              Spacer(modifier = Modifier.width(5.dp))

           IconButton(modifier = Modifier.size(25.dp)
               .clickable { true }
               .clip(CircleShape),
               onClick = {null}
                ) {
               Image(painter = painterResource(R.drawable.forward),
                   contentDescription = null)
           }
       }
        Spacer(modifier = Modifier.height(10.dp))
       LazyRow(modifier = Modifier.fillMaxWidth()
           .height(150.dp)
           .padding(2.dp)) {

           item() {
               Spacer(modifier= Modifier.width(10.dp))
               Card(elevation = CardDefaults.cardElevation(4.dp)
               ) {
                   Image(modifier = Modifier.width(250.dp)
                       .height(150.dp),
                       painter = painterResource(R.drawable.woodworking),
                       contentDescription = null,
                       contentScale = ContentScale.Crop,
                       )
                   Spacer(Modifier.width(12.dp))
               }
           }

           item() {
               Spacer(modifier= Modifier.width(10.dp))
               Card(elevation = CardDefaults.cardElevation(4.dp)
               ) {
                   Image(modifier = Modifier.width(250.dp)
                       .height(150.dp),
                       painter = painterResource(R.drawable.electrical_person),
                       contentDescription = null,
                       contentScale = ContentScale.Crop,
                   )
                   Spacer(Modifier.width(12.dp))
               }
           }

           item() {
               Spacer(modifier= Modifier.width(10.dp))
               Card(elevation = CardDefaults.cardElevation(4.dp)
               ) {
                   Image(modifier = Modifier.width(250.dp)
                       .height(150.dp),
                       painter = painterResource(R.drawable.builder_person),
                       contentDescription = null,
                       contentScale = ContentScale.Crop,
                   )
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
