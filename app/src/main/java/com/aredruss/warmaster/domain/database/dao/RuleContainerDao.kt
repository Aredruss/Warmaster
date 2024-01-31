package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.RuleContainer

@Dao
interface RuleContainerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<RuleContainer>)

    @Query("SELECT * FROM RuleContainer WHERE :id = ruleSectionId ORDER BY displayOrder")
    suspend fun getItemsById(id: String): List<RuleContainer>
}