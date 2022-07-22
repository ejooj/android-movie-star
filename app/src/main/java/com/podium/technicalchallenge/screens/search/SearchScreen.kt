package com.podium.technicalchallenge.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.podium.technicalchallenge.screens.components.MoviesList
import com.podium.technicalchallenge.screens.components.StarTopBar
import com.podium.technicalchallenge.screens.search.SearchViewModel.GetSearchDataResult

@Composable
fun SearchScreen(
    searchState: GetSearchDataResult,
    navController: NavController,
    queryGenre: String
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
    ) {
        StarTopBar()
        MoviesList(searchState, queryGenre)
    }
}