package ru.shcherbakov.weatherapp.domain

class AddFactUseCase(private val factListRepository: FactListRepository) {
    private fun addFact(fact: Fact){
        factListRepository.addFact(fact)
    }
}