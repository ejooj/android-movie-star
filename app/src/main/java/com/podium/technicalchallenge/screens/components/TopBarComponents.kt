package com.podium.technicalchallenge.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.podium.technicalchallenge.screens.theme.MyAppTextFieldColors

@Composable
fun StarTopBar() {
    Column {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 0.dp
        ) {
            RoundIcon()
            Text(
                text = "Movie Star",
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Start,
                color = Color.White,
                maxLines = 1,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(start = 12.dp)
            )
        }
        Divider(
            modifier = Modifier.padding(vertical = 2.dp),
            color = MaterialTheme.colors.primary,
            thickness = 3.dp,
            startIndent = 0.dp
        )
    }
}

@Composable
fun SearchTopBar(text: String, onChange: (String) -> Unit) {
    Column {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 0.dp
        ) {
            RoundIcon(Icons.Outlined.Search)
            TextField(
                value = text,
                onValueChange = onChange,
                label = { Text(text = "Search here...") },
                textStyle = MaterialTheme.typography.subtitle1,
                colors = MyAppTextFieldColors(),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(start = 12.dp)
            )
        }
        Divider(
            modifier = Modifier.padding(vertical = 2.dp),
            color = MaterialTheme.colors.primary,
            thickness = 3.dp,
            startIndent = 0.dp
        )
    }
}

@Composable
fun RoundIcon(icon: ImageVector = Icons.Outlined.Star) {
    val circleSize = 42.dp
    Box {
        Box(
            modifier = Modifier
                .size(circleSize + 2.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
        Box(
            modifier = Modifier
                .size(circleSize)
                .clip(CircleShape)
                .background(MaterialTheme.colors.primary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StarTopBar()
}