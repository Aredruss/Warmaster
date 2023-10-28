package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.UnitCompositionMiniature

@Dao
interface UnitCompositionMiniatureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<UnitCompositionMiniature>)

    @Query("SELECT * FROM UnitCompositionMiniature")
    suspend fun getAll(): List<UnitCompositionMiniature>

    @Query("SELECT * FROM UnitCompositionMiniature WHERE unitCompositionId IN (:ids)")
    suspend fun getItemsByUnitCompId(ids: List<String>): List<UnitCompositionMiniature>
    @Query("SELECT * FROM UnitCompositionMiniature WHERE :id = miniatureId")
    suspend fun getItemsByMini(id: String): List<UnitCompositionMiniature>

}