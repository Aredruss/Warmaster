package com.aredruss.warmaster.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.Amendment

@Dao
interface AmendmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Amendment>)

    @Query("SELECT * FROM Amendment WHERE :id = publicationId ORDER  BY displayOrder")
    suspend fun getItemsByPublication(id: String): List<Amendment>
}