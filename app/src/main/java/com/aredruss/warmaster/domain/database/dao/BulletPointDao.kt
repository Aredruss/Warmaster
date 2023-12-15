package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.BulletPoint

@Dao
interface BulletPointDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<BulletPoint>)

    @Query("SELECT * FROM BulletPoint WHERE :id = ruleContainerComponentId ORDER by displayOrder")
    suspend fun getItemsByRuleComponentId(id: String): List<BulletPoint>
}