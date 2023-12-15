package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.Detachment

@Dao
interface DetachmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Detachment>)

    @Query("SELECT * FROM Detachment WHERE :id = id")
    suspend fun getItemById(id: String): Detachment

    @Query("SELECT * FROM Detachment WHERE id in (:ids)")
    suspend fun getItemsByIds(ids: List<String>?): List<Detachment>
}