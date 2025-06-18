package ru.shcherbakov.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.shcherbakov.weatherapp.domain.Fact
import ru.shcherbakov.weatherapp.domain.FactListRepository
import javax.inject.Inject

class FactListRepositoryImpl @Inject constructor(private val factService: FactService): FactListRepository {

    private var factListLD = MutableLiveData<List<Fact>>()
    private val factList = mutableListOf<Fact>()

    override fun addFact(fact: Fact) {
        // Реализация добавления факта
    }

    override fun deleteFact(fact: Fact) {
        // Реализация удаления факта
    }

    override suspend fun getFact(): Fact = factService.getRandom()

    override suspend fun getFactList(type: String, participants: Int): LiveData<List<Fact>> {
        try {
            val newFacts = factService.getFiltered(type, participants)
            factList.clear()
            factList.addAll(newFacts)
            updateLiveData()
        } catch (e: Exception) {
            factListLD.postValue(emptyList())
        }
        return factListLD
    }




    private fun updateLiveData() {
        factListLD.postValue(factList.toList())
    }
}