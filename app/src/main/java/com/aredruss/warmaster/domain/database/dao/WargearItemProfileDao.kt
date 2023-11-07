package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.WargearItemProfile

@Dao
interface WargearItemProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<WargearItemProfile>)

    @Query("SELECT * FROM WargearItemProfile WHERE wargearItemId IN (:ids)")
    suspend fun getItemsById(ids: List<String>): List<WargearItemProfile>
}