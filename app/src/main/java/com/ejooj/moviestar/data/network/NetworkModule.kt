package com.ejooj.moviestar.data.network

import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import com.ejooj.moviestar.HomeDataQuery
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL =
        "https://podium-fe-challenge-2021.netlify.app/.netlify/functions/graphql"

    @Singleton
    @Provides
    fun providesApolloClient(): ApolloClient = ApolloClient.Builder()
        .serverUrl(BASE_URL)
        .build()

    @Singleton
    @Provides
    fun provideGetHomeDataQuery(client: ApolloClient): ApolloCall<HomeDataQuery.Data> {
        return client.query(HomeDataQuery())
    }
}