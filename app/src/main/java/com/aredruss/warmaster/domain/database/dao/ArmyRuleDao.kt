package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.ArmyRule

@Dao
interface ArmyRuleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<ArmyRule>)

    @Query("SELECT * FROM ArmyRule WHERE :id = id")
    suspend fun getItemsById(id: String): List<ArmyRule>

    @Query("SELECT * FROM ArmyRule WHERE :id = publicationId")
    suspend fun getItemsByPubId(id: String): List<ArmyRule>
}