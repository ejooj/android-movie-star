package com.podium.technicalchallenge.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.podium.technicalchallenge.screens.components.StarTopBar
import com.podium.technicalchallenge.screens.components.TopMoviesList
import com.podium.technicalchallenge.screens.theme.MyApplicationTheme
import com.podium.technicalchallenge.screens.home.HomeViewModel.GetHomeDataResult

@Composable
fun HomeScreen(homeData: GetHomeDataResult) {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(modifier = Modifier.statusBarsPadding()) {
                StarTopBar()
                TopMoviesList(homeData)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen(GetHomeDataResult.Loading)
}