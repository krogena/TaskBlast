package ru.shcherbakov.weatherapp.domain

import androidx.lifecycle.LiveData
import ru.shcherbakov.weatherapp.data.FactService
import javax.inject.Inject

interface FactListRepository {
    fun addFact(fact: Fact)

    fun deleteFact(fact: Fact)

    fun getFact(factId: Int): Fact?

    fun getFactList(): LiveData<List<Fact>>
}