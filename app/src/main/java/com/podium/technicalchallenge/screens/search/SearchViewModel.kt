package com.podium.technicalchallenge.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.podium.technicalchallenge.HomeDataQuery
import com.podium.technicalchallenge.SearchQuery
import com.podium.technicalchallenge.screens.home.HomeViewModel
import com.podium.technicalchallenge.screens.search.usecases.GetSearchData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchData: GetSearchData
) : ViewModel() {
    private val _searchData = MutableLiveData<GetSearchDataResult>()
    val searchData: LiveData<GetSearchDataResult> = _searchData

    suspend fun querySearch(offset: Int, genre: String, search: String) {
        getSearchData(offset, genre, search)
            .transform {
                it?.let { emit(it) }
            }.map {
                GetSearchDataResult.OnSuccess(it)
            }.catch<GetSearchDataResult> {
                emit(GetSearchDataResult.OnError(GetSearchDataResult.Error.UNKNOWN))
            }.collect {
                _searchData.postValue(it)
            }
    }

    sealed class GetSearchDataResult {
        data class OnSuccess(
            val value: SearchQuery.Data
        ) : GetSearchDataResult()

        data class OnError(
            val error: Error
        ) : GetSearchDataResult()

        object Loading : GetSearchDataResult()

        enum class Error(val message: String) {
            UNKNOWN("Something went wrong, please try again later")
        }
    }
}