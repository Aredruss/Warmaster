package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.Strategem

@Dao
interface StrategemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Strategem>)

    @Query("SELECT * FROM Strategem WHERE :id = publicationId ORDER BY displayOrder")
    suspend fun getItemsByPublicationId(id: String): List<Strategem>

    @Query("SELECT * FROM Strategem WHERE :detachmentId = detachmentId ORDER BY displayOrder")
    suspend fun getItemsByDetachmentId(detachmentId: String): List<Strategem>

    @Query("SELECT * FROM Strategem WHERE :id = id")
    suspend fun getItemId(id: String): Strategem?
}