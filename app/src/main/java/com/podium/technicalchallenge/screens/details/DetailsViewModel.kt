package com.podium.technicalchallenge.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.podium.technicalchallenge.DetailQuery
import com.podium.technicalchallenge.SearchQuery
import com.podium.technicalchallenge.screens.details.usecases.GetMovieDetails
import com.podium.technicalchallenge.screens.search.SearchViewModel
import com.podium.technicalchallenge.screens.search.usecases.GetSearchData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import org.junit.rules.ErrorCollector
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieDetails: GetMovieDetails
) : ViewModel() {
    private val _details = MutableLiveData<GetDetailsResult>()
    val details: LiveData<GetDetailsResult> = _details

    suspend fun queryDetails(id: Int) {
        getMovieDetails(id)
            .transform {
                it?.let { emit(it) }
            }.map {
                GetDetailsResult.OnSuccess(it)
            }.catch<GetDetailsResult> {
                emit(GetDetailsResult.OnError(GetDetailsResult.Error.UNKNOWN))
            }.collect {
                _details.postValue(it)
            }
    }

    sealed class GetDetailsResult {
        data class OnSuccess(
            val value: DetailQuery.Data
        ) : GetDetailsResult()

        data class OnError(
            val error: Error
        ) : GetDetailsResult()

        object Loading : GetDetailsResult()

        enum class Error(val message: String) {
            UNKNOWN("Something went wrong, please try again later")
        }
    }
}