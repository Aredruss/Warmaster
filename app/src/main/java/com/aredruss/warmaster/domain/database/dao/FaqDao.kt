package com.aredruss.warmaster.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.Faq

@Dao
interface FaqDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Faq>)

    @Query("SELECT * FROM Faq WHERE :id = publicationId ORDER  BY displayOrder")
    suspend fun getItemsByPublication(id: String): List<Faq>
}