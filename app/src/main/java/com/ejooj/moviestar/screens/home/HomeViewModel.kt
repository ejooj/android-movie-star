package com.ejooj.moviestar.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ejooj.moviestar.HomeDataQuery
import com.ejooj.moviestar.screens.home.usecases.GetHomeData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeData: GetHomeData
) : ViewModel() {
    private val _homeData = MutableLiveData<GetHomeDataResult>()
    val homeData: LiveData<GetHomeDataResult> = _homeData

    suspend fun queryForHomeData() {
        getHomeData()
            .transform {
                it?.let { emit(it) }
            }.map {
                GetHomeDataResult.OnSuccess(it)
            }.catch<GetHomeDataResult> {
                emit(GetHomeDataResult.OnError(GetHomeDataResult.Error.UNKNOWN))
            }.collect {
                _homeData.postValue(it)
            }
    }

    sealed class GetHomeDataResult {
        data class OnSuccess(
            val value: HomeDataQuery.Data
        ) : GetHomeDataResult()

        data class OnError(
            val error: Error
        ) : GetHomeDataResult()

        object Loading : GetHomeDataResult()

        enum class Error(val message: String) {
            UNKNOWN("Something went wrong, please try again later")
        }
    }
}