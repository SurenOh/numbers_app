package com.example.myapplication.api

import com.example.myapplication.apimodels.ApiResponseError
import com.example.myapplication.apimodels.response.NumberDto

sealed class DataResult<out T: Any> {
    class Success<out T: Any>(val data: T) : DataResult<T>()
    class Failure(val data: ApiResponseError) : DataResult<Nothing>()
    class FailureHttp(val error: String) : DataResult<Nothing>()
}

typealias NumberResponseCallback = (DataResult<NumberDto>) -> Unit