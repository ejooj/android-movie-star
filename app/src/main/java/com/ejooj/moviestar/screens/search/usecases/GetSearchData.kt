package com.ejooj.moviestar.screens.search.usecases

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.ejooj.moviestar.SearchQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchData @Inject constructor(
    val apolloClient: ApolloClient
) {
    operator fun invoke(offset: Int, genre: String, search: String): Flow<SearchQuery.Data?> = flow {
        val movies = apolloClient.query(SearchQuery(
            Optional.presentIfNotNull(offset),
            Optional.presentIfNotNull(genre),
            Optional.presentIfNotNull(search),
        )).execute().data
        emit(movies)
    }
}