package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.LoadoutChoiceWargearItem

@Dao
interface LoadoutChoiceWargearItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<LoadoutChoiceWargearItem>)

    @Query("SELECT wargearItemId FROM LoadoutChoiceWargearItem WHERE loadoutChoiceId IN (:ids)")
    suspend fun getItemsByIds(ids: List<String>): List<String>
}