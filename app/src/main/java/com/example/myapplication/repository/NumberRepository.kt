package com.example.myapplication.repository

interface NumberRepository {
    fun getNumberFact(number: String, completionHandler: NumberResultStatus)
    fun getRandomNumberFact(completionHandler: NumberResultStatus)
}