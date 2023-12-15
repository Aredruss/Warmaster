package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.DetachmentFactionKeyword

@Dao
interface DetachmentFactionKeywordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<DetachmentFactionKeyword>)

    @Query("SELECT detachmentId FROM DetachmentFactionKeyword WHERE :id = factionKeywordId")
    suspend fun getItemsByFactionId(id: String): List<String>
}