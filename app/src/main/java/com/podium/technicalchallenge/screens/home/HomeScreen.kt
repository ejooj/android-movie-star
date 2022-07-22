package com.podium.technicalchallenge.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.podium.technicalchallenge.screens.components.GenresList
import com.podium.technicalchallenge.screens.components.StarTopBar
import com.podium.technicalchallenge.screens.components.TopMoviesList
import com.podium.technicalchallenge.screens.home.HomeViewModel.GetHomeDataResult

@Composable
fun HomeScreen(homeData: GetHomeDataResult, navController: NavController) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
    ) {
        StarTopBar()
        TopMoviesList(homeData) {
            navController.navigate("details/$it")
        }
        when (homeData) {
            is GetHomeDataResult.OnSuccess -> {
                GenresList(genres = homeData.value.genres) {
                    navController.navigate("search?genre=$it") {
                        popUpTo("home")
                    }
                }
            }
        }
    }
}