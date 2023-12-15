package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.Publication

@Dao
interface PublicationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Publication>)

    @Query("SELECT factionKeywordId FROM Publication WHERE :id = id")
    suspend fun getFactionKeywordId(id: String): String?

    @Query("SELECT * FROM Publication WHERE :id = factionKeywordId ORDER BY name")
    suspend fun getByFactionKeywordId(id: String): List<Publication>

    @Query("SELECT * FROM Publication")
    suspend fun getPublications(): List<Publication>
}