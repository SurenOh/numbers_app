package com.example.myapplication.mapper

import com.example.myapplication.apimodels.response.NumberDto
import com.example.myapplication.model.NumberModel
import com.example.myapplication.util.Mapper

class NumberDtoMapper : Mapper<NumberDto, NumberModel>{
    override fun mapToModel(value: NumberDto) = NumberModel(
        number = value.fact.split(" ")[0],
        fact = value.fact
    )

    override fun mapFromModel(model: NumberModel) = NumberDto(model.fact ?: "")

}