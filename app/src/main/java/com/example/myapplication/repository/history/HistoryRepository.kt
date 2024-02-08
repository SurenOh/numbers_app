package com.example.myapplication.repository.history

import com.example.myapplication.model.NumberModel

interface HistoryRepository {
    suspend fun getHistory(): List<NumberModel>
    suspend fun insertHistory(history: NumberModel)
}