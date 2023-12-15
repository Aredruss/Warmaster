package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.DetachmentRule

@Dao
interface DetachmentRuleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<DetachmentRule>)

    @Query("SELECT * FROM DetachmentRule WHERE :id = detachmentId")
    suspend fun getItemsById(id: String): List<DetachmentRule>
}