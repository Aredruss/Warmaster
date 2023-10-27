package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.Datasheet
import kotlinx.coroutines.flow.Flow

@Dao
interface DatasheetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Datasheet>)

    @Query("SELECT * FROM Datasheet WHERE :id = id")
    suspend fun getItemById(id: String): Datasheet

    @Query("SELECT * FROM Datasheet WHERE id IN (:ids) ORDER BY displayOrder")
    suspend fun getItemsByIds(ids: List<String>): List<Datasheet>
}