package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.WargearOptionGroup

@Dao
interface WargearOptionGroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<WargearOptionGroup>)

    @Query("SELECT id FROM WargearOptionGroup WHERE datasheetId =:id")
    suspend fun getItemsByDatasheet(id: String): List<String>

}