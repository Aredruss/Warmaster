package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.FactionKeyword

@Dao
interface FactionKeywordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<FactionKeyword>)

    @Query("SELECT EXISTS (SELECT * FROM FactionKeyword WHERE :parentId = parentFactionKeywordId)")
    suspend fun checkSubFactionsExist(parentId: String): Boolean

    @Query("SELECT * FROM FactionKeyword")
    suspend fun getFactions(): List<FactionKeyword>

    @Query("SELECT * FROM FactionKeyword WHERE :parentId = parentFactionKeywordId OR :parentId = id")
    suspend fun getSubFactions(parentId: String): List<FactionKeyword>
}