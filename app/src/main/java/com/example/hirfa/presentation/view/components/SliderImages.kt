package com.example.hirfa.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hirfa.R


@Composable
fun SliderImages(modifier: Modifier = Modifier){
    Column(modifier = Modifier.fillMaxWidth().wrapContentHeight()){
        Row (modifier= Modifier.fillMaxWidth() ,
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