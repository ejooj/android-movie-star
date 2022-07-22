package com.podium.technicalchallenge.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.podium.technicalchallenge.screens.components.GenresList
import com.podium.technicalchallenge.screens.components.StarTopBar
import com.podium.technicalchallenge.screens.components.TopMoviesList
import com.podium.technicalchallenge.screens.home.HomeViewModel.GetHomeDataResult
import com.podium.technicalchallenge.screens.theme.MyApplicationTheme

@Composable
fun HomeScreen(homeData: GetHomeDataResult) {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(modifier = Modifier
                .statusBarsPadding()
                ) {
                StarTopBar()
                TopMoviesList(homeData)
                when (homeData) {
                    is GetHomeDataResult.OnSuccess -> {
                        GenresList(genres = homeData.value.genres) {}
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen(GetHomeDataResult.Loading)
}