package com.example.myapplication.repository

import com.example.myapplication.model.NumberModel

sealed class NumberStatus {
    class StatusError(val error: String) : NumberStatus()
    class StatusErrorHttp(val error: String) : NumberStatus()
    class StatusSuccess(val result: NumberModel) : NumberStatus()
}
