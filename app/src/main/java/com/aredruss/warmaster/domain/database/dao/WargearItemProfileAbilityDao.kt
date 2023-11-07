package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.WargearItemProfileAbility

@Dao
interface WargearItemProfileAbilityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<WargearItemProfileAbility>)

    @Query("SELECT wargearAbilityId FROM WargearItemProfileAbility WHERE :id = wargearItemProfileId")
    suspend fun getItemsById(id: String): List<String>
}