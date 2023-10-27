package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.DatasheetRule
import kotlinx.coroutines.flow.Flow

@Dao
interface DatasheetRuleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<DatasheetRule>)

    @Query("SELECT * FROM DatasheetRule WHERE :id = datasheetId ORDER BY displayOrder")
    suspend fun getItemById(id: String): List<DatasheetRule>
}