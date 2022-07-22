package com.ejooj.moviestar.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ejooj.moviestar.screens.details.DetailsScreen
import com.ejooj.moviestar.screens.details.DetailsViewModel
import com.ejooj.moviestar.screens.home.HomeScreen
import com.ejooj.moviestar.screens.home.HomeViewModel
import com.ejooj.moviestar.screens.search.SearchScreen
import com.ejooj.moviestar.screens.search.SearchViewModel
import com.ejooj.moviestar.screens.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val detailsViewModel: DetailsViewModel by viewModels()

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchHomeData()
        setContent {
            val navController = rememberNavController()
            val homeState by homeViewModel.homeData.observeAsState(HomeViewModel.GetHomeDataResult.Loading)
            val searchState by searchViewModel.searchData.observeAsState(SearchViewModel.GetSearchDataResult.Loading)
            val detailsState by detailsViewModel.details.observeAsState(DetailsViewModel.GetDetailsResult.Loading)
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeScreen(homeState, navController) }
                        composable(
                            "details/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) {
                            val arg = it.arguments?.getInt("id")!!
                            LaunchedEffect(key1 = arg) {
                                fetchMovieDetails(arg)
                            }
                            DetailsScreen(detailsState)
                        }
                        composable(
                            "search?genre={genre}",
                            arguments = listOf(navArgument("genre") { defaultValue = "" })
                        ) {
                            val arg = it.arguments?.getString("genre")!!
                            LaunchedEffect(key1 = arg) {
                                fetchSearchData(arg)
                            }
                            SearchScreen(searchState, navController, arg) { genre, search ->
                                fetchSearchData(genre, search)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun fetchHomeData() {
        lifecycleScope.launch {
            homeViewModel.queryForHomeData()
        }
    }

    private fun fetchSearchData(genre: String, search: String = "") {
        if (searchJob?.isActive ?: false) {
            searchJob?.cancel()
        }
        searchJob = lifecycleScope.launch {
            searchViewModel.querySearch(0, genre, search)
        }
    }

    private fun fetchMovieDetails(id: Int) {
        lifecycleScope.launch {
            detailsViewModel.queryDetails(id)
        }
    }
}