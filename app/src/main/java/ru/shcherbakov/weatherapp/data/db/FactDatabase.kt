package ru.shcherbakov.weatherapp.data.db

import androidx.room.RoomDatabase

abstract class FactDatabase: RoomDatabase() {
    abstract fun getFactDao(): FactDao

    companion object{
        @Volatile
        private var instance: FactDatabase? = null
    }
}