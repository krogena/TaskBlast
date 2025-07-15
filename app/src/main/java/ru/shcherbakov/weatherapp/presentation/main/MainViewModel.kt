package ru.shcherbakov.weatherapp.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.shcherbakov.weatherapp.data.FactListRepositoryImpl
import ru.shcherbakov.weatherapp.domain.Fact
import ru.shcherbakov.weatherapp.utils.Resource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: FactListRepositoryImpl): ViewModel() {

    val factLiveData: MutableLiveData<Resource<Fact>> = MutableLiveData()

    // Для списка избранных
    private val _favoritesLiveData = MutableLiveData<List<Fact>>(emptyList())
    val favoritesLiveData: LiveData<List<Fact>> = _favoritesLiveData

    init {
        Log.d("MyLog", "MainViewModel initialized")
        getFact()
    }

    fun getFact() =
        viewModelScope.launch {
            factLiveData.postValue(Resource.Loading())
            try {
                val fact = repository.getFact()  // Получаем Fact напрямую
                factLiveData.value = Resource.Success(fact)
            } catch (e: Exception) {
                factLiveData.value = Resource.Error(e.message)
            }
        }

    fun addToFavorites(fact: Fact) {
        val current = _favoritesLiveData.value.orEmpty()
        // Проверяем на дубликаты по ключу (или другому уникальному полю)
        if (current.none { it.key == fact.key }) {
            _favoritesLiveData.value = current + fact
            Log.d("MyLog", "Added: ${fact.activity}")
        }
    }

    fun removeFromFavorites(fact: Fact) {
        _favoritesLiveData.value = _favoritesLiveData.value
            ?.filterNot { it.key == fact.key }
            .orEmpty()
    }
}