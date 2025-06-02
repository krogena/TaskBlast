package ru.shcherbakov.weatherapp.domain

class GetFactUseCase(private val factListRepository: FactListRepository) {
    private fun getFact(factId: Int): Fact?{
        return factListRepository.getFact(factId)
    }
}