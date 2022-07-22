package com.podium.technicalchallenge.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Column(modifier = Modifier.padding(vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
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
fun ListHeader(text: String, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .heightIn(min = 56.dp)
            .padding(start = 24.dp)
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

@Preview
@Composable
fun ItemPreview() {
    MovieCard()
}

@Preview
@Composable
fun Preview() {
    TopMoviesList(Loading)
}