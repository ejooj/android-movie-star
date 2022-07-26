package com.ejooj.moviestar.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ejooj.moviestar.screens.components.MoviesList
import com.ejooj.moviestar.screens.components.SearchTopBar
import com.ejooj.moviestar.screens.search.SearchViewModel.GetSearchDataResult

@Composable
fun SearchScreen(
    searchState: GetSearchDataResult,
    navController: NavController,
    queryGenre: String,
    onSearch: (String, String) -> Unit
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
    ) {
        var searchText by rememberSaveable { mutableStateOf("") }

        SearchTopBar(searchText) {
            searchText = it
            onSearch(queryGenre, it)
        }
        MoviesList(searchState, queryGenre) {
            navController.navigate("details/$it")
        }
    }
}