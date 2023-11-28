package com.aredruss.warmaster.domain.database.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aredruss.warmaster.domain.database.index.FavoriteUnit
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteUnitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: FavoriteUnit)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(items: List<FavoriteUnit>)

    @Query("DELETE FROM FavoriteUnit WHERE datasheetId = :datasheetId")
    suspend fun deleteUnit(datasheetId: String)

    @Query("SELECT EXISTS (SELECT 1 FROM FavoriteUnit WHERE :datasheetId = datasheetId)")
    fun checkIfFavorite(datasheetId: String): Flow<Boolean>

    @Query("SELECT * FROM FavoriteUnit")
    suspend fun getAllItems(): List<FavoriteUnit>

    @Query("SELECT datasheetId FROM FavoriteUnit WHERE :factionId = factionId")
    suspend fun getIdsByFactionId(factionId: String): List<String>
}