package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.Miniature

@Dao
interface MiniatureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Miniature>)

    @Query("SELECT * FROM Miniature WHERE :id = datasheetId ORDER  BY displayOrder")
    suspend fun getItemsByDatasheet(id: String): List<Miniature>
}