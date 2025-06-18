package ru.shcherbakov.weatherapp.domain

class GetFactUseCase(private val factListRepository: FactListRepository) {
    private suspend fun getFact(): Fact {
        return factListRepository.getFact()
    }
}