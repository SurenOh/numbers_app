package com.example.myapplication.mapper

import com.example.myapplication.db.entity.HistoryEntity
import com.example.myapplication.model.NumberModel
import com.example.myapplication.util.Mapper

class HistoryEntityMapper : Mapper<HistoryEntity, NumberModel>{

    override fun mapToModel(value: HistoryEntity) = NumberModel(
        number = value.number,
        fact = value.fact
    )

    override fun mapFromModel(model: NumberModel) = HistoryEntity(
        number = model.number,
        fact = model.fact ?: ""
    )

}