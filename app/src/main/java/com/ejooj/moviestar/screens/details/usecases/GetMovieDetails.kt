package com.ejooj.moviestar.screens.details.usecases

import com.apollographql.apollo3.ApolloClient
import com.ejooj.moviestar.DetailQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetails @Inject constructor(
    private val apolloClient: ApolloClient
){
    operator fun invoke(id: Int): Flow<DetailQuery.Data?> = flow {
        val details = apolloClient.query(
            DetailQuery(id)
        ).execute().data
        emit(details)
    }
}