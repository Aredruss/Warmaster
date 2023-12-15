package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.Enhancement

@Dao
interface EnhancementDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Enhancement>)

    @Query("SELECT * FROM Enhancement WHERE :id = id")
    suspend fun getItemsById(id: String): List<Enhancement>

    @Query("SELECT * FROM Enhancement WHERE :id = detachmentId ORDER BY displayOrder")
    suspend fun getItemsByDetachmentId(id: String): List<Enhancement>

    @Query("SELECT * FROM Enhancement WHERE :id = publicationId ORDER BY displayOrder")
    suspend fun getItemsByPublicationId(id: String): List<Enhancement>
}