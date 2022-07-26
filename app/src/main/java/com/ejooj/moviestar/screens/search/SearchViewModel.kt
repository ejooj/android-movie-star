package com.ejooj.moviestar.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ejooj.moviestar.SearchQuery
import com.ejooj.moviestar.screens.search.usecases.GetSearchData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
            }.cancellable().collect {
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