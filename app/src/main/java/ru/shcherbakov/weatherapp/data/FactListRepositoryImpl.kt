package ru.shcherbakov.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.shcherbakov.weatherapp.domain.Fact
import ru.shcherbakov.weatherapp.domain.FactListRepository
import javax.inject.Inject

class FactListRepositoryImpl @Inject constructor(private val factService: FactService): FactListRepository {

    private val factListLD = MutableLiveData<List<Fact>>()
    private val factList = mutableListOf<Fact>()

    override fun addFact(fact: Fact) {
        // Реализация добавления факта
    }

    override fun deleteFact(fact: Fact) {
        // Реализация удаления факта
    }

    override fun getFact(factId: Int): Fact? {
        return factList.find { it.key.toInt() == factId}
    }

    override fun getFactList(): LiveData<List<Fact>> {
        return factListLD
    }

    suspend fun loadRandomFact() {
        try {
            val fact = withContext(Dispatchers.IO) {
                factService.getRandom()
            }
            factList.add(fact)
            updateLiveData()
        } catch (e: Exception) {
            // Обработка ошибок (например, логирование или уведомление UI)
            e.printStackTrace()
        }
    }

    suspend fun loadFilteredFacts(type: String, participants: Int) {
        try {
            val facts = withContext(Dispatchers.IO) {
                factService.getFiltered(type, participants)
            }
            factList.clear()
            factList.addAll(facts)
            updateLiveData()
        } catch (e: Exception) {
            // Обработка ошибок
            e.printStackTrace()
        }
    }
    private fun updateLiveData() {
        factListLD.postValue(factList.toList())
    }
}