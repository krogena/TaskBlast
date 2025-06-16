package ru.shcherbakov.weatherapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.shcherbakov.weatherapp.data.FactListRepositoryImpl
import ru.shcherbakov.weatherapp.data.FactService
import ru.shcherbakov.weatherapp.domain.Fact
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val factService: FactService): ViewModel() {

}