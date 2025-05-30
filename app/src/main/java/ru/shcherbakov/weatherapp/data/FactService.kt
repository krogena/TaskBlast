package ru.shcherbakov.weatherapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface FactService {
    @GET("/random")
    suspend fun getRandom()

    @GET("/filter")
    suspend fun getFiltered(
        @Query("type") type: String,
        @Query("participants") participants: Int,
    )
}