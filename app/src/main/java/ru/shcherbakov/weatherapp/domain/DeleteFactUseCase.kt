package ru.shcherbakov.weatherapp.domain

class DeleteFactUseCase(private val factListRepository: FactListRepository) {
    private fun deleteFact(fact: Fact){
        factListRepository.deleteFact(fact)
    }
}