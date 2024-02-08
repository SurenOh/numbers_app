package com.example.myapplication.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.db.entity.HistoryEntity

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(entity: HistoryEntity)

    @Query("SELECT * FROM HistoryEntity")
    suspend fun getHistory(): List<HistoryEntity>

    @Query("DELETE FROM HistoryEntity WHERE id = (SELECT min(id) FROM HistoryEntity)")
    suspend fun deleteOldestHistory()
}