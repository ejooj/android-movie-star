package com.podium.technicalchallenge.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.podium.technicalchallenge.screens.home.RoundStar

@Composable
fun StarTopBar() {
    Column {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 0.dp
        ) {
            RoundStar()
            Text(
                text = "Movie Star",
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Start,
                color = Color.White,
                maxLines = 1,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically).padding(start = 12.dp)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StarTopBar()
}