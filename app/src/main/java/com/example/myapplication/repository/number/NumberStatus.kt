package com.example.myapplication.repository.number

import com.example.myapplication.model.NumberModel

typealias NumberResultStatus = (NumberStatus) -> Unit

sealed class NumberStatus {
    class StatusError(val error: String) : NumberStatus()
    class StatusErrorHttp(val error: String) : NumberStatus()
    class StatusSuccess(val result: NumberModel) : NumberStatus()
}