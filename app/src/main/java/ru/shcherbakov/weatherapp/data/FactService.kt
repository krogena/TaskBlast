package ru.shcherbakov.weatherapp.data

import retrofit2.http.GET
import retrofit2.http.Query
import ru.shcherbakov.weatherapp.domain.Fact

interface FactService {

    //Получение рандомного факта
    @GET("/random")
    suspend fun getRandom(): Fact

    //Получение списка занятий по фильтрам type (вид) и participants (кол-во участников)
    @GET("/filter")
    suspend fun getFiltered(
        @Query("type") type: String,
        @Query("participants") participants: Int,
    ): List<Fact>
}