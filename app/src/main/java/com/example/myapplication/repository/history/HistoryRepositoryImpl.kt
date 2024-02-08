package com.example.myapplication.repository.history

import com.example.myapplication.db.MyDatabase
import com.example.myapplication.mapper.HistoryEntityMapper
import com.example.myapplication.model.NumberModel

class HistoryRepositoryImpl(db: MyDatabase, private val mapper: HistoryEntityMapper): HistoryRepository {
    private val historyDao = db.getNumberDao()

    override suspend fun getHistory(): ArrayList<NumberModel> {
        if (historyDao.getHistory().size > MAX_HISTORY_SIZE) historyDao.deleteOldestHistory()
        return ArrayList(mapper.mapListToModel(historyDao.getHistory().sortedBy { it.id }))
    }

    override suspend fun insertHistory(history: NumberModel) {
        historyDao.insertHistory(mapper.mapFromModel(history))
    }

    companion object {
        const val MAX_HISTORY_SIZE = 20
    }
}