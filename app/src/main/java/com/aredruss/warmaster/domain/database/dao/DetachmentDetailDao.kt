package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.DetachmentDetail

@Dao
interface DetachmentDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<DetachmentDetail>)

    @Query("SELECT * FROM DetachmentDetail WHERE :id = detachmentId")
    suspend fun getItemById(id: String): DetachmentDetail?
}