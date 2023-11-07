package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.LoadoutChoice

@Dao
interface LoadoutChoiceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<LoadoutChoice>)

    @Query("SELECT id FROM LoadoutChoice WHERE loadoutChoiceSetId IN (:ids)")
    suspend fun getItemsByIds(ids: List<String>): List<String>
}