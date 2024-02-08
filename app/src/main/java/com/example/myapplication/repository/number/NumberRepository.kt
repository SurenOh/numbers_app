package com.example.myapplication.repository.number

interface NumberRepository {
    fun getNumberFact(number: String, completionHandler: NumberResultStatus)
    fun getRandomNumberFact(completionHandler: NumberResultStatus)
}