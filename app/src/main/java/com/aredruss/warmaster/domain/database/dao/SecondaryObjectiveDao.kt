package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.SecondaryObjective

@Dao
interface SecondaryObjectiveDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<SecondaryObjective>)

    @Query("SELECT * FROM SecondaryObjective WHERE :id = publicationId")
    suspend fun getItemsByPublicationId(id: String): List<SecondaryObjective>
}