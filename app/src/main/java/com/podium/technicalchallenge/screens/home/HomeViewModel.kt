package com.podium.technicalchallenge.screens.home

import androidx.lifecycle.ViewModel
import com.podium.technicalchallenge.screens.home.usecases.IGetHomeData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeData: IGetHomeData
): ViewModel() {

}