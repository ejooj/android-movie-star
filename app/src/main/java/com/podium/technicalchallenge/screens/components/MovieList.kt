package com.podium.technicalchallenge.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.podium.technicalchallenge.HomeDataQuery
import com.podium.technicalchallenge.R
import com.podium.technicalchallenge.screens.home.HomeViewModel
import com.podium.technicalchallenge.screens.home.HomeViewModel.GetHomeDataResult.Loading
import com.podium.technicalchallenge.screens.home.HomeViewModel.GetHomeDataResult.OnSuccess
import com.valentinilk.shimmer.shimmer

@Composable
fun TopMoviesList(homeData: HomeViewModel.GetHomeDataResult) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        ListHeader(text = "Top 5 Movies \uD83C\uDF7F", icon = Icons.Outlined.ArrowForward)
        LazyRow(
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (homeData) {
                is OnSuccess -> {
                    val movies = homeData.value.movies
                    if (movies != null) {
                        items(movies) {
                            MovieCard(movie = it)
                        }
                    }
                }
                is Loading -> {
                    items(List(5) {}) {
                        MovieCard(modifier = Modifier.shimmer())
                    }
                }
            }
        }
    }
}

@Composable
fun GenresList(genres: List<String>, onClick: (String) -> Unit) {
    ListHeader(text = "Browse by genre", icon = Icons.Outlined.List)
    LazyRow(
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(genres) { item ->
            GenreCard(genre = item, onClick = { onClick(item) })
        }
    }
}

@Composable
fun ListHeader(text: String, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .heightIn(min = 56.dp)
            .padding(start = 24.dp, top = 22.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .wrapContentWidth(Alignment.Start)
        )
        IconButton(
            onClick = { /* todo */ },
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = icon,
                tint = MaterialTheme.colors.primary,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun MovieCard(modifier: Modifier = Modifier, movie: HomeDataQuery.Movie? = null) {
    Card(
        modifier = modifier
            .size(
                width = 155.dp,
                height = 250.dp
            ),
        shape = RoundedCornerShape(20.dp),
        elevation = 4.dp
    ) {
        Box {
            val imgModifier = Modifier
                .fillMaxSize()
            if (movie != null) {
                AsyncImage(
                    modifier = imgModifier,
                    model = movie.posterPath,
                    placeholder = painterResource(id = R.drawable.placeholder_movie),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.placeholder_movie),
                    modifier = imgModifier,
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun GenreCard(modifier: Modifier = Modifier, genre: String, onClick: (String) -> Unit) {
    Card(
        modifier = modifier
            .size(
                width = 180.dp,
                height = 100.dp
            ).padding(top = 32.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 4.dp
    ) {
        Button(onClick = { onClick(genre) }) {
            Text(
                text = genre,
                style = MaterialTheme.typography.h4,
                color = Color.White,
                modifier = Modifier
                    .wrapContentWidth(Alignment.Start)
            )
        }
    }
}

@Preview
@Composable
fun ItemPreview() {
    MovieCard()
}

@Preview
@Composable
fun TopMoviesPreview() {
    TopMoviesList(Loading)
}