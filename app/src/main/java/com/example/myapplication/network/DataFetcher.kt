package com.example.myapplication.network

import com.example.myapplication.api.ApiResult
import com.example.myapplication.api.DataResult
import com.example.myapplication.apimodels.ApiResponseError
import com.example.myapplication.apimodels.response.NumberDto
import com.google.gson.Gson

open class DataFetcher {

    internal inline fun <reified T: Any> callback(crossinline completionHandler: (DataResult<T>) -> Unit): ApiResponse {
        return { apiResult ->
            val result = when (apiResult) {
                is ApiResult.FailureHttp -> DataResult.FailureHttp(apiResult.errorMessage)
                is ApiResult.Failure -> {
                    try {
                        val errorModel = Gson().fromJson(apiResult.errorJson, ApiResponseError::class.java)
                        DataResult.Failure(errorModel)
                    } catch (e: Exception) {
                        DataResult.FailureHttp("Cannot convert error: ${apiResult.errorJson}")
                    }
                }
                is ApiResult.Success -> {
                    try {
                        DataResult.Success(Gson().fromJson(apiResult.dataJson, T::class.java))
                    } catch (e: Exception) {
                        try {
                            DataResult.Success(NumberDto(apiResult.dataJson) as T)
                        } catch (ex: Exception) {
                            DataResult.FailureHttp("Cannot convert: ${apiResult.dataJson}")
                        }
                    }
                }
            }
            completionHandler(result)
        }
    }
}