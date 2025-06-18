package ru.shcherbakov.weatherapp.domain

import androidx.lifecycle.LiveData

interface FactListRepository {
    fun addFact(fact: Fact)

    fun deleteFact(fact: Fact)

    suspend fun getFact(): Fact

    suspend fun getFactList(type: String, participants: Int): LiveData<List<Fact>>
}