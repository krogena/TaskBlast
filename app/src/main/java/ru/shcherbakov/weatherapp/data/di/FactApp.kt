package ru.shcherbakov.weatherapp.data.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Инициализация Hilt в приложении
@HiltAndroidApp
class FactApp: Application()