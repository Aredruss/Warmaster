package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.RuleSection

@Dao
interface RuleSectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<RuleSection>)

    @Query("SELECT * FROM RuleSection WHERE :id = publicationId ORDER BY displayOrder")
    suspend fun getItemsById(id: String): List<RuleSection>

    @Query("SELECT * FROM RuleSection WHERE :id = parentId ORDER BY displayOrder")
    suspend fun getChildItemsById(id: String): List<RuleSection>
}