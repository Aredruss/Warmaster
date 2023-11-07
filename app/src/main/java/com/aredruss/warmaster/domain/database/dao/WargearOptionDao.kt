package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.WargearOption

@Dao
interface WargearOptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<WargearOption>)

    @Query("SELECT * FROM WargearOption WHERE :id = id")
    suspend fun getItemsById(id: String): List<WargearOption>

    @Query("SELECT wargearItemId FROM WargearOption WHERE wargearOptionGroupId IN (:ids)")
    suspend fun getItemsByGroups(ids: List<String>): List<String>
}