package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.DatasheetSubAbility

@Dao
interface DatasheetSubAbilityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<DatasheetSubAbility>)

    @Query("SELECT * FROM DatasheetSubAbility WHERE :id = datasheetAbilityId")
    suspend fun getItemsById(id: String): List<DatasheetSubAbility>
}