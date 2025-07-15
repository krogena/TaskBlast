package ru.shcherbakov.weatherapp.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.shcherbakov.weatherapp.data.FactService
import ru.shcherbakov.weatherapp.domain.Fact.Companion.BASE_URL
import javax.inject.Singleton


/**
 * Модуль Dagger Hilt для предоставления зависимостей уровня приложения.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Предоставляет базовый URL для API.
    @Provides
    fun baseUrl() = BASE_URL


    // Создает и настраивает HTTP-логгер для отладки сетевых запросов.
    // Устанавливает уровень логирования BODY для вывода тел запросов и ответов.
    @Provides
    fun logging() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // Создает и настраивает OkHttpClient с добавленным логгером.
    @Provides
    fun okHttpClient() = OkHttpClient.Builder().addInterceptor(logging()).build()

    // Предоставляет Retrofit-клиент как синглтон для работы с API.
    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): FactService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient())
        .build()
        .create(FactService::class.java)

//    @Provides
//    @Singleton
//    fun provideFactDatabase(@ApplicationContext context: Context){
//        Room.databaseBuilder(
//            context,
//            FactDatabase::class.java,
//            "factDatabase"
//        ).build()
//    }

//    @Provides
//    fun provideFactDao(appDatabase: FactDatabase): FactDao{
//        return appDatabase.getFactDao()
//    }
}