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
fun ServiceText(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { onClick() }
    ) {
        Text(
            text = "Service",
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.width(5.dp))

        IconButton(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape),
            onClick = onClick
        ) {
            Image(
                painter = painterResource(R.drawable.forward),
                contentDescription = "Go to Service",
            )
        }
    }
}

@Composable
fun CraftsmenText(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { onClick() }
    ) {
        Text(
            text = "Craftsmen",
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.width(5.dp))

        IconButton(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape),
            onClick = onClick
        ) {
            Image(
                painter = painterResource(R.drawable.forward),
                contentDescription = "Go to Craftsmen",
            )
        }
    }
}
