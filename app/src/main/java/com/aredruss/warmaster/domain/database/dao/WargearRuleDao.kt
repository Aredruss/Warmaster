package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.WargearRule

@Dao
interface WargearRuleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<WargearRule>)

    @Query("SELECT * FROM WargearRule WHERE :id = datasheetId")
    suspend fun getItemsById(id: String): List<WargearRule>
}