package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.UnitComposition

@Dao
interface UnitCompositionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<UnitComposition>)

    @Query("SELECT * FROM UnitComposition")
    suspend fun getAll(): List<UnitComposition>

    @Query("SELECT * FROM UnitComposition WHERE :id = datasheetId")
    suspend fun getItemById(id: String): List<UnitComposition>
}