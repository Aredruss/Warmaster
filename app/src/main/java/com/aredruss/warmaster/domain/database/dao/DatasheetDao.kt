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

    @Query("SELECT * FROM Datasheet WHERE :id = id ORDER BY name")
    suspend fun getItemById(id: String): Datasheet

    @Query("SELECT * FROM Datasheet WHERE id IN (:ids) ORDER BY name")
    suspend fun getItemsByIds(ids: List<String>): List<Datasheet>

    @Query("SELECT * FROM Datasheet WHERE name LIKE '%' || :query || '%' ORDER BY name")
    fun getItemsByName(query: String): Flow<List<Datasheet>>

    @Query("SELECT * FROM Datasheet WHERE :id = publicationId ORDER BY name")
    fun getDataByPubId(id: String): List<Datasheet>
}