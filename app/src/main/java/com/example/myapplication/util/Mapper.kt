package com.example.myapplication.util

interface Mapper<V, M> {
    fun mapToModel(value: V) : M
    fun mapFromModel(model: M) : V

    fun mapListToModel(list: List<V>) = list.map { mapToModel(it) }
}