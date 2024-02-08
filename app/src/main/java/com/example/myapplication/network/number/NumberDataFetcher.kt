package com.example.myapplication.network.number

import com.example.myapplication.api.NumberResponseCallback
import com.example.myapplication.api.number.NumberApi
import com.example.myapplication.network.ApiService
import com.example.myapplication.network.DataFetcher

class NumberDataFetcher(private val apiService: ApiService) : DataFetcher() {

    fun getNumberData(number: String, completionHandler: NumberResponseCallback) {
        apiService.procedure(
            api = NumberApi.GetNumberData(number),
            completionHandler = callback(completionHandler)
        )
    }

    fun getRandomNumberData(completionHandler: NumberResponseCallback) {
        apiService.procedure(
            api = NumberApi.GetRandomNumberData,
            completionHandler = callback(completionHandler)
        )
    }

}