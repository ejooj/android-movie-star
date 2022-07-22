package com.podium.technicalchallenge.data.network

import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import com.podium.technicalchallenge.HomeDataQuery
import com.podium.technicalchallenge.SearchQuery
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