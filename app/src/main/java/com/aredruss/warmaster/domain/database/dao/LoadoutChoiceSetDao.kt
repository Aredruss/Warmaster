package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.LoadoutChoiceSet

@Dao
interface LoadoutChoiceSetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<LoadoutChoiceSet>)

    @Query("SELECT id FROM LoadoutChoiceSet WHERE :id = datasheetId")
    suspend fun getItemsByIds(id: String): List<String>
}