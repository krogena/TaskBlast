package ru.shcherbakov.weatherapp.domain

import androidx.lifecycle.LiveData

class GetFactListUseCase (private val factListRepository: FactListRepository){
    private fun getFactList(): LiveData<List<Fact>>{
        return factListRepository.getFactList()
    }
}