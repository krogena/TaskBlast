package ru.shcherbakov.weatherapp.domain

data class Fact(
    val accessibility: String,
    val activity: String,
    val availability: Double,
    val duration: String,
    val key: String,
    val kidFriendly: Boolean,
    val link: String,
    val participants: Int,
    val price: Double,
    val type: String,
){
    companion object{
        const val BASE_URL = "https://bored-api.appbrewery.com/"
    }
}