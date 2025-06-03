package ru.shcherbakov.weatherapp.data

import retrofit2.http.GET
import retrofit2.http.Query
import ru.shcherbakov.weatherapp.domain.Fact

interface FactService {
    @GET("/random")
    suspend fun getRandom(): Fact

    @GET("/filter")
    suspend fun getFiltered(
        @Query("type") type: String,
        @Query("participants") participants: Int,
    ): List<Fact>
}