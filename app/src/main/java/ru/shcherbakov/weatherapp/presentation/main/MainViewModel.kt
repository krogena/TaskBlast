package ru.shcherbakov.weatherapp.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.shcherbakov.weatherapp.data.FactListRepositoryImpl
import ru.shcherbakov.weatherapp.data.FactService
import ru.shcherbakov.weatherapp.domain.Fact
import ru.shcherbakov.weatherapp.utils.Resource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: FactListRepositoryImpl): ViewModel() {

    val factLiveData: MutableLiveData<Resource<Fact>> = MutableLiveData()

    init {
        getFact()
    }

    fun getFact() =
        viewModelScope.launch {
            factLiveData.postValue(Resource.Loading())
            try {
                val fact = repository.getFact()  // Получаем Fact напрямую
                factLiveData.value = Resource.Success(fact)
            } catch (e: Exception) {
                factLiveData.value = Resource.Error("Ошибка: ${e.message}")
            }
        }
}