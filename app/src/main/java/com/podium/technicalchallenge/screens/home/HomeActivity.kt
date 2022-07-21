package com.podium.technicalchallenge.screens.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.podium.technicalchallenge.screens.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.podium.technicalchallenge.screens.home.HomeViewModel.GetHomeDataResult
import com.podium.technicalchallenge.screens.home.HomeViewModel.GetHomeDataResult.Loading
import com.podium.technicalchallenge.screens.home.HomeViewModel.GetHomeDataResult.OnSuccess
import com.podium.technicalchallenge.screens.home.HomeViewModel.GetHomeDataResult.Error

@AndroidEntryPoint
class HomeActivity: ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }

    private fun fetchData() {
        lifecycleScope.launch {
            viewModel.queryForHomeData()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {

    }
}