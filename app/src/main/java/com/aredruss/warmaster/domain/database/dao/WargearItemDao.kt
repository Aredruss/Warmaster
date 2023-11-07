package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.WargearItem

@Dao
interface WargearItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<WargearItem>)

    @Query("SELECT * FROM WargearItem WHERE :id = id")
    suspend fun getItemsById(id: String): List<WargearItem>

    @Query("SELECT * FROM WargearItem WHERE id IN (:ids)")
    suspend fun getItemsByIds(ids: List<String>): List<WargearItem>
}