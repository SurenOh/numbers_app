package com.example.myapplication.api

sealed class ApiResult {
    class Success(val dataJson: String) : ApiResult()
    class Failure(val errorJson: String) : ApiResult()
    class FailureHttp(val errorMessage: String) : ApiResult()
}
