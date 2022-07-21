package com.podium.technicalchallenge.data.model


data class MovieQuery(
    val data: Object
)

data class Data(
    val movies: List<Movie>
)

data class Movie(
    val title: String
)