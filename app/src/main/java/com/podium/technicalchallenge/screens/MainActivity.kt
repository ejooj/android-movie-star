package com.podium.technicalchallenge.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.podium.technicalchallenge.screens.home.HomeScreen
import com.podium.technicalchallenge.screens.home.HomeViewModel
import com.podium.technicalchallenge.screens.search.SearchScreen
import com.podium.technicalchallenge.screens.search.SearchViewModel
import com.podium.technicalchallenge.screens.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchHomeData()
        setContent {
            val navController = rememberNavController()
            val homeState by homeViewModel.homeData.observeAsState(HomeViewModel.GetHomeDataResult.Loading)
            val searchState by searchViewModel.searchData.observeAsState(SearchViewModel.GetSearchDataResult.Loading)
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeScreen(homeState, navController) }
                        composable(
                            "search/{genre}",
                            arguments = listOf(navArgument("genre") { type = NavType.StringType })
                        ) {
                            val arg = it.arguments?.getString("genre")!!
                            fetchSearchData(arg)
                            SearchScreen(searchState, navController, arg)
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

    private fun fetchSearchData(genre: String) {
        lifecycleScope.launch {
            searchViewModel.querySearch(0, genre, "")
        }
    }
}