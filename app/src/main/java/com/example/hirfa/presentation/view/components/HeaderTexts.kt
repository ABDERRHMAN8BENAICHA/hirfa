package com.example.hirfa.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hirfa.R


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

@Composable
fun CarftsmenText(modifier: Modifier = Modifier){
    Row (verticalAlignment = Alignment.CenterVertically){

        Text("Craftsmen",
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