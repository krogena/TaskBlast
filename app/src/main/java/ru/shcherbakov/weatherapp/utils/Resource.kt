package ru.shcherbakov.weatherapp.utils

/**
 * Универсальный sealed-класс для инкапсуляции состояния загрузки ресурса.
 * Используется для обработки данных, которые загружаются асинхронно (например, из сети или БД).
 *
 * @param T тип загружаемых данных
 * @property data собственно загруженные данные (доступны только в состоянии Success)
 * @property message сообщение об ошибке (доступно только в состоянии Error)
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
){
    class Success<T>(data: T?): Resource<T>(data = data)
    class Error<T>(message: String?): Resource<T>(message = message)
    class Loading<T>: Resource<T>()
}