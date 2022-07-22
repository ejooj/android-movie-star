package com.ejooj.moviestar.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ejooj.moviestar.screens.components.MovieCard
import com.ejooj.moviestar.screens.components.StarTopBar

@Composable
fun DetailsScreen(state: DetailsViewModel.GetDetailsResult) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        StarTopBar()
        when (state) {
            is DetailsViewModel.GetDetailsResult.OnSuccess -> {
                state.value.movie?.let {
                    Row(modifier = Modifier.padding(14.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        MovieCard(movieId = it.id, poster = it.posterPath)
                        Card(
                            elevation = 4.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                                Text(
                                    text = "Rating: ${it.voteAverage}",
                                    style = MaterialTheme.typography.h4,
                                    color = MaterialTheme.colors.primary,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )
                                Text(
                                    text = "Director: ${it.director.name}",
                                    style = MaterialTheme.typography.h4,
                                    color = MaterialTheme.colors.primary,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )
                            }
                        }
                    }
                    Card(
                        elevation = 4.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column {
                            LargeDetailsText(text = it.title)
                            LargeDetailsText(text = it.overview, MaterialTheme.typography.h5)
                            LargeDetailsText(text = "Movie Cast")
                            it.cast.forEach { actor ->
                                LargeDetailsText(text = "${actor.character} played by ${actor.name}", MaterialTheme.typography.h5)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LargeDetailsText(text: String, style: TextStyle = MaterialTheme.typography.h4) {
    Text(
        text = text,
        style = style,
        color = MaterialTheme.colors.primary,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}