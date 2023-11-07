package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.model.MiniatureKeyword

@Dao
interface MiniatureKeywordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<MiniatureKeyword>)

    @Query("SELECT keywordId FROM MiniatureKeyword WHERE miniatureId = :id")
    suspend fun getItemsById(id: String): List<String>
}