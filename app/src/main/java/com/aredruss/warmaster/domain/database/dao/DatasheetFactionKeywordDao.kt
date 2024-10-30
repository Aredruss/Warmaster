package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.DatasheetFactionKeyword

@Dao
interface DatasheetFactionKeywordDao {

    @Insert
    suspend fun insert(items: List<DatasheetFactionKeyword>)

    @Query("SELECT datasheetId FROM datasheetFactionKeyword WHERE factionKeywordId = :factionId")
    suspend fun getAllDatasheetsByFaction(factionId: String): List<String>

    @Query("SELECT COUNT(*) FROM datasheetFactionKeyword WHERE :datasheetId = datasheetId")
    suspend fun getDatasheetFactionCount(datasheetId: String): Int
}