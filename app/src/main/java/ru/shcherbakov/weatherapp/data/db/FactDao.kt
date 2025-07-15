package ru.shcherbakov.weatherapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.shcherbakov.weatherapp.domain.Fact

@Dao
interface FactDao {
    //Получить список занятий
    @Query("SELECT * FROM facts")
    suspend fun getAllFacts(): List<Fact>

    //Вставить занятие
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fact: Fact)

    //Удалить занятие
    @Delete
    suspend fun delete(fact: Fact)
}