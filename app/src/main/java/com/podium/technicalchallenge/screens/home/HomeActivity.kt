package com.podium.technicalchallenge.screens.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchData()
        setContent {
            val homeState by viewModel.homeData.observeAsState(HomeViewModel.GetHomeDataResult.Loading)
            HomeScreen(homeState)
        }
    }

    private fun fetchData() {
        lifecycleScope.launch {
            viewModel.queryForHomeData()
        }
    }
}