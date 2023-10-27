package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.DatasheetAbility

@Dao
interface DatasheetAbilityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<DatasheetAbility>)

    @Query("SELECT * FROM DatasheetAbility WHERE id IN (:ids)")
    suspend fun getItemsById(ids: List<String>): List<DatasheetAbility>
}