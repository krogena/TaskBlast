package ru.shcherbakov.weatherapp.presentation.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.shcherbakov.weatherapp.data.FactService
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val factService: FactService): ViewModel() {

}