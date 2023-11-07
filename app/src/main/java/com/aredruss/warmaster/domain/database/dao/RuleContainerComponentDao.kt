package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.RuleContainerComponent

@Dao
interface RuleContainerComponentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<RuleContainerComponent>)

    @Query("SELECT * FROM RuleContainerComponent WHERE :id = armyRuleId ORDER BY displayOrder")
    suspend fun getItemsById(id: String): List<RuleContainerComponent>
}