package ru.shcherbakov.weatherapp.domain

import androidx.lifecycle.LiveData

class GetFactListUseCase (private val factListRepository: FactListRepository){
    private suspend fun getFactList(type: String, participants: Int): LiveData<List<Fact>>{
        return factListRepository.getFactList(type, participants)
    }
}