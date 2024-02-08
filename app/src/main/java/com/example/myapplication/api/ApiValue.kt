package com.example.myapplication.api

enum class ApiRequestType {
    Get, GetWithParams, Post, Put, Delete
}

class ApiValue(path: String, requestType: ApiRequestType) {
    val path: String
    val type: ApiRequestType = requestType

    init {
        this.path = "$BASE_URL$path"
    }

    companion object {
        const val BASE_URL = "http://numbersapi.com/"
    }
}