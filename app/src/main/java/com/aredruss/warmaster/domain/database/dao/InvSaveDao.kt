package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.InvSave

@Dao
interface InvSaveDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<InvSave>)

    @Query("SELECT * FROM InvSave")
    suspend fun getAll(): List<InvSave>

    @Query("SELECT * FROM InvSave WHERE :id = datasheetId")
    suspend fun getItemById(id: String): InvSave
}