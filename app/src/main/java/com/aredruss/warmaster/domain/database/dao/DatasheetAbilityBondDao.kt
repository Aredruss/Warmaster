package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.DatasheetAbilityBond

@Dao
interface DatasheetAbilityBondDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<DatasheetAbilityBond>)

    @Query("SELECT * FROM DatasheetAbilityBond")
    suspend fun getAll(): List<DatasheetAbilityBond>

    @Query("SELECT * FROM DatasheetAbilityBond WHERE :id = datasheetId")
    suspend fun getItemsById(id: String): List<DatasheetAbilityBond>
}