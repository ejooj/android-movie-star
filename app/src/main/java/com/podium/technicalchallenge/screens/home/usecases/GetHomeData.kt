package com.podium.technicalchallenge.screens.home.usecases

import com.apollographql.apollo3.ApolloCall
import com.podium.technicalchallenge.HomeDataQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHomeData @Inject constructor(
    private val getHomeQuery: ApolloCall<HomeDataQuery.Data>
) {
    operator fun invoke(): Flow<HomeDataQuery.Data?> = flow {
        val movies = getHomeQuery.execute().data
        emit(movies)
    }
}