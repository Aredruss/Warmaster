package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.DetachmentDetailBulletPoint

@Dao
interface DetachmentDetailBulletPointDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<DetachmentDetailBulletPoint>)

    @Query("SELECT * FROM DetachmentDetailBulletPoint WHERE :id = detachmentDetailId")
    suspend fun getItemsByDetachmentDetailId(id: String): List<DetachmentDetailBulletPoint>
}