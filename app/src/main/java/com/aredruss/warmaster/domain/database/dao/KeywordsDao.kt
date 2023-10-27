package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.Keyword

@Dao
interface KeywordsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Keyword>)

    @Query("SELECT * FROM Keyword WHERE id IN (:ids)")
    suspend fun getItemsById(ids: List<String>): List<Keyword>
}