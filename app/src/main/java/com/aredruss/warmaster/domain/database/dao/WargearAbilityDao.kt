package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.WargearAbility

@Dao
interface WargearAbilityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<WargearAbility>)

    @Query("SELECT * FROM WargearAbility WHERE id in (:ids)")
    suspend fun getItemsById(ids: List<String>): List<WargearAbility>
}